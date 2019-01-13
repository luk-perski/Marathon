package pl.perski.lukasz.maraton.ui.act.training

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData
import pl.perski.lukasz.maraton.data.repositories.ExercisesRepository
import pl.perski.lukasz.maraton.utils.CONST_STRINGS
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class TrainingModel : TrainingActivityMVP.Model {

    lateinit var repository: ExercisesRepository
    lateinit var colRefExercises: DocumentReference
    lateinit var colRefExercisesSheet: DocumentReference
    private val auth = FirebaseAuth.getInstance()
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getTrainingEnd(intent: Intent): Int {
        return intent.getIntExtra(CONST_STRINGS.TRAINING_END, 0)
    }

    fun saveToDB(listToSave: ArrayList<ExerciseDoneData>) {

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


        //TODO: dodaj onSucced listenery i daj fabtoasty
        //zapis ćwiczeń do dokumentów
        for (eDD in listToSave) {
            val exerciseSheet = HashMap<String, Any>()
            exerciseSheet.put(eDD.recId.toString(), eDD.done)
            colRefExercisesSheet.update(exerciseSheet)
            colRefExercises.collection(dayMonthYear).document(eDD.recId.toString()).set(eDD)
            exerciseSheet.clear()
        }
    }

    override fun getExercisesFromDBByTitles(context: Context, intent: Intent): List<ExerciseData>? {
        repository = ExercisesRepository(context)
        val titles = intent.getStringArrayExtra(CONST_STRINGS.TRAINING_ENTER_DATA)
        val exercises = mutableListOf<ExerciseData>()
        titles.forEach {
            repository.getExerciseByTitle(it)?.let { it1 -> exercises.add(it1) }
        }
        return exercises
    }

    override fun getExercisesFromDB(context: Context): List<ExerciseData> {
        repository = ExercisesRepository(context)
        return repository.getExercises()
    }
}

