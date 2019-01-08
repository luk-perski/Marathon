package pl.perski.lukasz.maraton.ui.act.calendar

import android.content.Context
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData

interface CalendarActivityMVP
{

interface View{
    fun getContext(): Context
    fun setList(exerciseDoneList : ArrayList<ExerciseDoneData>)
    fun changeTvTapInfo(state : Boolean)
    fun changeLvExercisesState(state : Boolean)
    fun manageProgressBar(value : Int)


}

    interface Presenter {
        fun setView(view: CalendarActivityMVP.View)
        fun getExercises(month: String, day : String)
    }

    interface Model{}
}