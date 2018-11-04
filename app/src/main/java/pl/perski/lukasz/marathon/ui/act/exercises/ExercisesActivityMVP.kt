package pl.perski.lukasz.marathon.ui.act.exercises

import android.content.Context
import pl.perski.lukasz.marathon.data.model.ExerciseData

interface ExercisesActivityMVP {


    interface View{
        fun setExercises(exercisesList : List<ExerciseData>)
        fun getContext(): Context
    }

    interface Presenter {
        fun setView(view: View)
        fun getExercises()
    }

    interface Model
    {
        fun getExercisesFromDB(context :Context) : List<ExerciseData>?
    }

}
