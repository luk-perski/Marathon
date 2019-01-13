package pl.perski.lukasz.maraton.ui.act.calendar

import android.content.Context
import android.os.Environment
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.itextpdf.text.BaseColor
import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.Rectangle
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import com.prolificinteractive.materialcalendarview.CalendarDay
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData
import pl.perski.lukasz.maraton.utils.CONST_STRINGS
import spencerstudios.com.fab_toast.FabToast
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@Suppress("NAME_SHADOWING")
class CalendarActivityPresenter : CalendarActivityMVP.Presenter {

    private lateinit var view: CalendarActivityMVP.View
    private lateinit var context: Context
    private val auth = FirebaseAuth.getInstance()
    private var exercisesDoneList = ArrayList<ExerciseDoneData>()
    private lateinit var filePath: String
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var SEPARATOR = ";"

    override fun setView(view: CalendarActivityMVP.View) {
        this.view = view
        context = view.getContext()
    }


    override fun getExercises(month: String, day: String) {
        lateinit var colRefDayExercise: CollectionReference

        view.changeTvTapInfo(false)
        view.manageProgressBar(View.VISIBLE)
        auth.uid?.let {
            colRefDayExercise = db.collection("users/$it/exercises/$month/$day")
        }

        //odczytywanie z bazy wszystkich dokumentów
        colRefDayExercise.get().addOnSuccessListener { documents ->
            view.manageProgressBar(View.GONE)
            if (documents.isEmpty) {
                view.changeLvExercisesState(false)
                view.changeTvTapInfo(true)
                FabToast.makeText(context, context.resources.getString(R.string.no_training_this_day),
                        FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_CENTER).show()
            } else {
                for (document in documents) {
                    exercisesDoneList.add(document.toObject(ExerciseDoneData::class.java))
                    view.changeTvTapInfo(false)
                    view.changeLvExercisesState(true)
                    Log.d("DEBUGAPP", document.id + " => " + document.data)
                }
                val filtered = exercisesDoneList!!.find { it.title.equals(CONST_STRINGS.MOOD) }
                when (filtered?.exerciseGroupId) {
                    0 -> FabToast.makeText(context, context.resources.getString(R.string.bad_mood),
                            FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_CENTER).show()
                    1 -> FabToast.makeText(context, context.resources.getString(R.string.fair_mood),
                            FabToast.LENGTH_LONG, FabToast.WARNING, FabToast.POSITION_CENTER).show()
                    2 -> FabToast.makeText(context, context.resources.getString(R.string.good_mood),
                            FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_CENTER).show()
                }
                exercisesDoneList.remove(filtered)
                view.setList(exercisesDoneList)
            }
        }
                .addOnFailureListener {
                    FabToast.makeText(context, it.message,
                            FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_CENTER).show()
                }
    }


    override fun generateExerciseSheet(date: CalendarDay) {
        // PRZYGOTOWANIE DOKUMENTU
        val cal = Calendar.getInstance()!!
        val time = cal.time!!
        val dateNow = SimpleDateFormat("yyyy_M_dd_HH_mm_ss", Locale.GERMANY).format(time)!!
        val month = date.month + 1
        val year = date.year
        lateinit var colRefDayExercise: CollectionReference

        filePath = Environment.getExternalStorageDirectory().absolutePath + "/" +
                view.getContext().resources.getString(R.string.app_name) + "/" +
                view.getContext().resources.getString(R.string.exercises_sheets)

        val directory = File(filePath)
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val file = File("$filePath/" +
                "${context.resources.getString(R.string.exercises_sheet)} ($dateNow).pdf")
        file.createNewFile()

        val exercisesCells = Array(55) { "" }
        //POBRANIE Z DANYCH Z FIRESTORE
        auth.uid?.let {
            colRefDayExercise = db.collection("users/$it/exercisesCard/$year/$month")

            colRefDayExercise.get().addOnSuccessListener { documents ->
                for (i in 0 until exercisesCells.size step 1) {
                    for (document in documents) {
                        if (document.contains(i.toString())) {
                            if (document.getBoolean(i.toString())!!) {
                                if (exercisesCells[i] != "") {
                                    exercisesCells[i] += "$SEPARATOR${document.id}"
                                } else {
                                    exercisesCells[i] += document.id
                                }
                            }
                        }
                    }
                }
                fillDocument(file, exercisesCells)
            }
        }
    }


    private fun fillDocument(file: File, exercisesCells: Array<String>) {
        //1470 x 500 DAJE ŁADNY, DWUSTRONNY DOKUMENT
        var model = CalendarModel()
        val pageSize = Rectangle(1470f, 1040f)
        val document = Document(pageSize, 1f, 1f, 32f, 32f)

        //ROZMIAR TABELI
        val table = PdfPTable(floatArrayOf
        (9f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f))
        table.defaultCell.horizontalAlignment = Element.ALIGN_CENTER

        //DODANIE NAGŁÓWKÓW
        table.addCell(context.resources.getString(R.string.exercise_title))
        for (i in 1 until table.numberOfColumns step 1) {
            table.addCell(i.toString())
        }

        table.headerRows = 1
        val cells = table.getRow(0).cells

        cells.forEach {
            it.backgroundColor = BaseColor.GRAY
        }

        //POBRANIE DANYCH Z LOKALNEJ BAZY DANYCH
        val exercises = model.getExercisesFromDB(context)
        exercises.forEach {
            table.addCell(it.title.normalize())
            val days = exercisesCells[it.recId].split(SEPARATOR)
            for (i in 1 until table.numberOfColumns step 1) {
                if (days.contains(i.toString())) {
                    table.addCell("x")
                } else {
                    table.addCell(" ")
                }
            }
        }

        //ZAPISANIE DOKUMENTU
        if (file.isFile) {
            PdfWriter.getInstance(document, FileOutputStream(file, false))
            document.open()
            document.add(table)
            document.close()
            FabToast.makeText(context, context.resources.getString(R.string.exercise_sheet_save),
                    FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_CENTER).show()
        }
    }


    private fun String.normalize(): String {
        val original = arrayOf("ą", "ć", "ę", "ł", "ń", "ó", "ś", "ź", "ż", "Ś")
        val normalized = arrayOf("a", "c", "e", "l", "n", "o", "s", "z", "z", "S")

        return this.map { it ->
            val index = original.indexOf(it.toString())
            if (index >= 0) normalized[index] else it
        }.joinToString("")
    }
}



