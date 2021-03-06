package pl.perski.lukasz.maraton.ui.act.training

import android.content.Context
import android.content.Intent
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData


interface TrainingActivityMVP {

    interface View {
        fun getContext(): Context
        fun setToolbarTittle(title: String)
        fun lockNextBtn()
        fun lockSkipBtn()
        fun unlockEndBtn()
        fun setExercisePicker(value: Float)
        fun setAmountQuestion(question: String)
        fun setEvents()
        fun setExerciseTitle(exerciseTitle: String)
        fun getExerciseAmount(): Int
        fun finishAct()
    }


    interface Presenter {
        fun setView(view: View)
        fun setControls()
        fun startTraining(intent: Intent)
        fun getExercises(intent: Intent)
        fun showAlertDialog()
    }


    interface Model {
        fun getExercisesFromDBByTitles(context: Context, intent: Intent): List<ExerciseData>?
        fun getTrainingEnd(intent: Intent): Int
        fun saveToDB(listToSave: ArrayList<ExerciseDoneData>, context: Context)
    }
}