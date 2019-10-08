package pl.perski.lukasz.maraton.ui.fragments.timer

import android.content.Context

interface TimerFragmentMVP

{
    interface View {
        fun getContext(): Context
        fun setToolbarTittle(title : String, subtitle: String)
        fun setEvents()
        fun setTimerValue(value : String)
        fun getMinuteValue(): Long
        fun getSecondsValue(): Long
        fun changeStartColor(state: Boolean)
        fun changeStartState(state: Boolean)
    }

    interface Presenter {
        fun setView(view: View)
        fun setControls()
        fun startTimer()
        fun stopTimer(buttonPressed : Boolean)
        fun resetTimer()
        fun convertTime(minutes : Long, seconds : Long): Long
    }

    interface Model
}