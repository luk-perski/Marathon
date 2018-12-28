package pl.perski.lukasz.maraton.ui.fragments.timer

import android.os.Handler
import android.os.SystemClock
import android.widget.Toast
import java.util.concurrent.TimeUnit

class TimerFragmentPresenter : TimerFragmentMVP.Presenter {
    override fun convertTime(minutes: Long, seconds: Long): Long {
        return  TimeUnit.MINUTES.toMillis(minutes) +
                TimeUnit.SECONDS.toMillis(seconds)
    }

    private lateinit var view: TimerFragmentMVP.View
    private var startTime = 0L
    private val customHandler = Handler()
    internal var timeInMilliseconds = 0L
    internal var timeTemp = 0L
    internal var updatedTime = 0L
    private var timerEndTime = 0L
    private var pickedMin = 0L
    private var pickedSec = 0L

    override fun setView(view: TimerFragmentMVP.View) {
        this.view = view
    }

    override fun setControls() {
        //TODO: zmień na R.string
        view.setToolbarTittle("Timer", "")
    }

    override fun startTimer() {
        pickedMin = view.getMinuteValue()
        pickedSec = view.getSecondsValue()
        timerEndTime = convertTime(pickedMin, pickedSec)
        view.setTimerValue(String.format("%02d", pickedMin)
                + ":"
                + String.format("%02d", pickedSec) )
        view.changeStartColor(false)
        view.changeStartState(false)
        startTime = SystemClock.uptimeMillis()
        customHandler.postDelayed(updateTimerThread, 0)
    }



    override fun stopTimer(buttonPressed : Boolean) {
        startTime = SystemClock.uptimeMillis()
        timeTemp = 0
        customHandler.removeCallbacks(updateTimerThread)

        view.setTimerValue(String.format("%02d", 0)
                + ":"
                + String.format("%02d", 0) )
        view.changeStartColor(true)
        view.changeStartState(true)
        //TODO: Na R.string
        if (timerEndTime >0L){
        Toast.makeText(view.getContext(), "Czas minął.",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun resetTimer() {
        TODO("not implemented") //To change titles of created functions use File | Settings | File Templates.
    }

    private val updateTimerThread = object : Runnable {
        override fun run() {
            //TODO: Dla "6 i luz" zrób specjalny tryb 5-3-7
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime
            updatedTime = timeTemp + timeInMilliseconds


            var seconds = (pickedSec - (updatedTime / 1000)).toInt()
            val minutes = pickedMin - (seconds / 60)
            seconds = seconds % 60


            var string = ""
            string += "" + String.format("%02d", minutes)
            string += ":" + String.format("%02d", seconds)

            if (updatedTime > timerEndTime)
            {
                stopTimer(false)
            }
            else
            {
                view.setTimerValue(string)
                customHandler.postDelayed(this, 0)
            }
        }
    }

}