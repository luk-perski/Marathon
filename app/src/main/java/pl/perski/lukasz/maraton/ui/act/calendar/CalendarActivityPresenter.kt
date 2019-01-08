package pl.perski.lukasz.maraton.ui.act.calendar

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData
import spencerstudios.com.fab_toast.FabToast
import java.text.SimpleDateFormat
import java.util.*

class CalendarActivityPresenter : CalendarActivityMVP.Presenter {


    private lateinit var  view : CalendarActivityMVP.View
    private lateinit var context: Context
    lateinit var colRefExercise: CollectionReference
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    var exercisesDoneList = ArrayList<ExerciseDoneData>()




    override fun getExercises(month: String, day: String) {
        view.changeTvTapInfo(false)
        view.manageProgressBar(View.VISIBLE)
        auth.uid?.let {
            colRefExercise = db.collection("users/$it/exercises/$month/$day")
        }

        //odczytywanie z bazy wszystkich dokumentów
        colRefExercise.get().addOnSuccessListener { documents ->
            view.manageProgressBar(View.GONE)
            if (documents.isEmpty) {
                view.changeLvExercisesState(false)
                view.changeTvTapInfo(true)
                FabToast.makeText(context, context.resources.getString(R.string.no_training_this_day), FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_CENTER).show()
            }

            for (document in documents) {
                exercisesDoneList.add(document.toObject(ExerciseDoneData::class.java))
                view.changeTvTapInfo(false)
                view.changeLvExercisesState(true)
                Log.d("DEBUGAPP", document.id + " => " + document.data)
            }

            view.setList(exercisesDoneList)

        }






     //   odczytywanie z bazy jednego dokumentu
//        colRefExercise.document("2").get().addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val document = task.result
//                if (document != null) {
//                    Log.i("DEBUGAPP", "kolano ${document}")
//                }
//            }
//        }
    }






    override fun setView(view: CalendarActivityMVP.View) {
        this.view = view
        context = view.getContext()    }

}