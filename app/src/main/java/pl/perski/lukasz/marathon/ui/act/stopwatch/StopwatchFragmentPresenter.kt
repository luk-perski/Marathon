package pl.perski.lukasz.marathon.ui.act.stopwatch

import android.os.Handler
import android.os.SystemClock

class StopwatchFragmentPresenter : StopwatchFragmentMVP.Presenter {

    private var startTime = 0L
    private val customHandler = Handler()
    internal var timeInMilliseconds = 0L
    internal var timeTemp = 0L
    internal var updatedTime = 0L


    override fun startStopwatch() {
        view.changeStartState(false)
        view.changeStartColor(false)
        view.changeResetState(true)
        view.changeResetColor(true)
        view.changeStopState(true)
        view.changeStopColor(true)
        startTime = SystemClock.uptimeMillis()
        customHandler.postDelayed(updateTimerThread, 0)

    }

    override fun stopStopwatch() {
        view.changeStopState(false)
        view.changeStopColor(false)
        view.changeStartState(true)
        view.changeStartColor(true)
        view.changeResetState(true)
        view.changeResetColor(true)
        timeTemp += timeInMilliseconds
        customHandler.removeCallbacks(updateTimerThread)
    }

    override fun resetStopwatch() {
        view.changeResetState(false)
        view.changeResetColor(false)
        view.changeStartState(true)
        view.changeStartColor(true)
        view.changeStopState(true)
        view.changeStopColor(true)
        view.setTimerValue(String.format("%02d", 0) + ":"
                + String.format("%02d", 0) + ":"
                + String.format("%01d", 0))
        startTime = SystemClock.uptimeMillis()
        timeTemp = 0
        customHandler.removeCallbacks(updateTimerThread)
    }


    private lateinit var view: StopwatchFragmentMVP.View


    override fun setView(view: StopwatchFragmentMVP.View) {
        this.view = view
    }

    override fun setControls() {

    }

    private val updateTimerThread = object : Runnable {
        override fun run() {
            //TODO: Dla "6 i luz" zrób specjalny tryb 5-3-7
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime
            updatedTime = timeTemp + timeInMilliseconds

            var seconds = (updatedTime / 1000).toInt()
            val minutes = seconds / 60
            seconds = seconds % 60
            val milliseconds = (updatedTime % 10).toInt()

            var string = ""
            string += "" + String.format("%02d", minutes)
            string += ":" + String.format("%02d", seconds)
            string += ":" + String.format("%01d", milliseconds)

            view.setTimerValue(string)
            customHandler.postDelayed(this, 0)
        }
    }
}