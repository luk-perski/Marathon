package pl.perski.lukasz.marathon.ui.act.stopwatch

import android.content.Context

interface StopwatchFragmentMVP {

    interface View {
        fun getContext(): Context
        fun setToolbarTittle(title : String, subtitle: String)
        fun setEvents()
        fun changeResetState(state : Boolean)
        fun changeStartState(state : Boolean)
        fun changeStopState(state : Boolean)
        fun changeResetColor(state : Boolean)
        fun changeStartColor(state : Boolean)
        fun changeStopColor(state : Boolean)
        fun setTimerValue(value : String)
    }

    interface Presenter {
        fun setView(view: StopwatchFragmentMVP.View)
        fun setControls()
        fun startStopwatch()
        fun stopStopwatch()
        fun resetStopwatch()
    }

    interface Model
    {}
}