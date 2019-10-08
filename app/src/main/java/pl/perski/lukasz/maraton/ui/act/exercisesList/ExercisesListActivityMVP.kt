package pl.perski.lukasz.maraton.ui.act.exercisesList

import android.content.Context
import pl.perski.lukasz.maraton.data.model.ExerciseData

interface ExercisesListActivityMVP {

    interface View{
        fun getContext(): Context
        fun setExercises(header: MutableList<String>, body: MutableList<MutableList<String>>)
        fun notifyDataSetChanged()
    }

    interface Presenter {
        fun setView(view: View)
        fun getExercises()
        fun getExercisesGroupId() : MutableList<Int>
        fun getExerciseTitlesGroup(id: Int) : MutableList<String>
        fun getExercisesGroup(id: Int): MutableList<ExerciseData>?
        fun addCustomTraining()
    }

    interface Model
    {
        fun getExercisesFromDB(context :Context) : List<ExerciseData>?
        fun getExercisesGroupIdFromDB (context :Context) : MutableList<Int>?
        fun getExerciseTitlesGroupFromDB(context :Context, groupId : Int) : MutableList<String>?
        fun getExercisesGroupFromDB(context :Context, groupId : Int) : MutableList<ExerciseData>?

    }
}
