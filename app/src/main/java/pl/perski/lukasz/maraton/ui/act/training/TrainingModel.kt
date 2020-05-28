package pl.perski.lukasz.maraton.ui.act.training

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData
import pl.perski.lukasz.maraton.data.repositories.ExercisesRepository
import pl.perski.lukasz.maraton.utils.ConstStrings
import spencerstudios.com.fab_toast.FabToast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class TrainingModel : TrainingActivityMVP.Model {

    lateinit var repository: ExercisesRepository
    lateinit var colRefExercises: DocumentReference
    lateinit var colRefExercisesSheet: DocumentReference
    private val auth = FirebaseAuth.getInstance()
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getTrainingEnd(intent: Intent): Int {
        return intent.getIntExtra(ConstStrings.TRAINING_END, 0)
    }

    override fun saveToDB(listToSave: ArrayList<ExerciseDoneData>, context: Context) {

        val cal = Calendar.getInstance()
        val time = cal.time
        val dayMonthYear = SimpleDateFormat("d-M-yyyy", Locale.GERMANY).format(time)
        val monthYear = SimpleDateFormat("M-yyyy", Locale.GERMANY).format(time)
        val month = SimpleDateFormat("M", Locale.GERMANY).format(time)
        val year = SimpleDateFormat("yyyy", Locale.GERMANY).format(time)
        val day = SimpleDateFormat("d", Locale.GERMANY).format(time)

        auth.uid?.let {
            colRefExercises = db.document("users/$it/exercises/$monthYear")
            colRefExercisesSheet = db.document("users/$it/exercisesCard/$year/$month/$day")
        }

        val dummy = HashMap<String, Any>()
        dummy.put("dummy", "dummy")
        colRefExercises.set(dummy)
        var firstSheet = true
        //SAVING EXERCISES IN DOCUMENTS
        for (eDD in listToSave) {
            val exerciseSheet = HashMap<String, Any>()
            exerciseSheet.put(eDD.recId.toString(), eDD.done)
            colRefExercises.collection(dayMonthYear).document(eDD.recId.toString())
                    .set(eDD).addOnFailureListener {
                        FabToast.makeText(context, it.message,
                                FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_CENTER).show()
                    }
            if (firstSheet) {
                firstSheet = false
                colRefExercisesSheet.set(exerciseSheet).addOnFailureListener {
                    FabToast.makeText(context, it.message,
                            FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_CENTER).show()
                }
            } else {
                colRefExercisesSheet.update(exerciseSheet).addOnFailureListener {
                    FabToast.makeText(context, it.message,
                            FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_CENTER).show()
                }
            }
        }
    }

    override fun getExercisesFromDBByTitles(context: Context, intent: Intent): List<ExerciseData>? {
        repository = ExercisesRepository(context)
        val titles = intent.getStringArrayExtra(ConstStrings.TRAINING_ENTER_DATA)
        val exercises = mutableListOf<ExerciseData>()
        titles.forEach {
            repository.getExerciseByTitle(it)?.let { it1 -> exercises.add(it1) }
        }
        return exercises
    }
}

