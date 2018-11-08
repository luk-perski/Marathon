package pl.perski.lukasz.marathon.ui.act.training

import android.content.Context
import pl.perski.lukasz.marathon.data.model.ExerciseData


interface TrainingActivityMVP {

    interface View {
        fun getContext(): Context
        fun setToolbarTittle(title : String, subtitle: String)
        fun lockNextBtn()
    }


    interface Presenter {
        fun setView(view: TrainingActivityMVP.View)
        fun startTraining()
        fun getMorningExercises()
    }


    interface Model
    {
        fun getExercisesFromDB(context : Context) : List<ExerciseData>?
    }
}