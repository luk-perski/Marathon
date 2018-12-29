package pl.perski.lukasz.maraton.ui.act.training

import android.content.Context
import pl.perski.lukasz.maraton.data.model.ExerciseData


interface TrainingActivityMVP {

    interface View {
        fun getContext(): Context
        fun setToolbarTittle(title : String)
        fun lockNextBtn()
        fun unlockEndBtn()
        fun setExercisePicker(value : Float)
        fun setAmountQuestion(question : String)
        fun setEvents()
        fun setExerciseTitle(exerciseTitle : String)
    }


    interface Presenter {
        fun setView(view: TrainingActivityMVP.View)
        fun startTraining()
        fun getMorningExercises()
        fun setControls()
    }


    interface Model
    {
        fun getExercisesFromDB(context : Context) : List<ExerciseData>?
    }
}