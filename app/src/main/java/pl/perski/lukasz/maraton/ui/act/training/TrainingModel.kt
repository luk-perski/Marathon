package pl.perski.lukasz.maraton.ui.act.training


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
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
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    lateinit var colRefExercise: DocumentReference


    @SuppressLint("SimpleDateFormat")
    fun saveToDB(listToSave : ArrayList<ExerciseDoneData>) {

        val cal = Calendar.getInstance()
        val time = cal.time
        val date = SimpleDateFormat("dd-M-yy", Locale.GERMANY).format(time)
        val month = SimpleDateFormat("M-yy", Locale.GERMANY).format(time)
//TODO: popraw ścieżkę
        auth.uid?.let {
            colRefExercise = db.document("users/$it/exercises/$date")
        }

        val dummy = HashMap<String, Any>()
        dummy.put("dummy", "dummy")
        colRefExercise.set(dummy)


        //zapis ćwiczeń do dokumentów
        for (x in listToSave) {
            colRefExercise.collection(month).document(x.recId.toString()).set(x)
        }

        //odczytywanie z bazy wszystkich dokumentów
//        colRefExercise.get().addOnCompleteListener { task ->
//            if (task.isSuccessful){
//                val myListOfDocuments = task.result?.documents
//            }
//        }

        //odczytywanie z bazy jednego dokumentu
//        colRefExercise.document("0").get().addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val document = task.result
//                if (document != null) {
//                    //Log.i("siemano", "kolano ${document}")
//                }
//            }
//        }
    }

    override fun getExercisesFromDBByTitles(context: Context, intent : Intent): List<ExerciseData>? {
        repository = ExercisesRepository(context)
        val titles = intent.getStringArrayExtra(CONST_STRINGS.TRAINING_ENTER_DATA)
        val exercises = mutableListOf<ExerciseData>()
        titles.forEach {
            exercises.add(repository.getExerciseByTitle(it))
        }
        return exercises
    }


    override fun getExercisesFromDB(context: Context): List<ExerciseData> {
        repository = ExercisesRepository(context)
        return repository.getExercises()
    }
}

