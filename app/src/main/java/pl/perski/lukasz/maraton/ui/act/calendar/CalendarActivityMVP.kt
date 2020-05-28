package pl.perski.lukasz.maraton.ui.act.calendar

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData

interface CalendarActivityMVP {

    interface View {
        fun getContext(): Context
        fun setList(exerciseDoneList: ArrayList<ExerciseDoneData>)
        fun changeTvTapInfo(state: Boolean)
        fun changeLvExercisesState(state: Boolean)
        fun manageProgressBar(value: Int)


    }

    interface Presenter {
        fun setView(view: View)
        fun getExercises(month: String, day: String)
        fun generateExerciseSheet(date: CalendarDay)
    }

    interface Model {
        fun getExercisesFromDB(context: Context): List<ExerciseData>
    }
}