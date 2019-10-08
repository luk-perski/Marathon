package pl.perski.lukasz.maraton.ui.fragments.timer

import android.content.Context
import android.os.Handler
import android.os.SystemClock
import android.widget.Toast
import pl.perski.lukasz.maraton.R
import spencerstudios.com.fab_toast.FabToast
import java.util.concurrent.TimeUnit

class TimerFragmentPresenter : TimerFragmentMVP.Presenter {

    private lateinit var view: TimerFragmentMVP.View
    lateinit var context: Context
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
        context = view.getContext()
    }

    override fun setControls() {
        view.setToolbarTittle(view.getContext().resources.getString(R.string.timer), "")
    }

    override fun startTimer() {
        pickedMin = view.getMinuteValue()
        pickedSec = view.getSecondsValue()
        timerEndTime = convertTime(pickedMin, pickedSec)
        pickedSec = timerEndTime / 1000
        view.setTimerValue(String.format("%02d", pickedMin)
                + ":"
                + String.format("%02d", pickedSec))
        view.changeStartColor(false)
        view.changeStartState(false)
        startTime = SystemClock.uptimeMillis()
        customHandler.postDelayed(updateTimerThread, 0)
    }

    override fun stopTimer(buttonPressed: Boolean) {
        startTime = SystemClock.uptimeMillis()
        timeTemp = 0
        customHandler.removeCallbacks(updateTimerThread)
        view.setTimerValue(String.format("%02d", 0)
                + ":"
                + String.format("%02d", 0))
        view.changeStartColor(true)
        view.changeStartState(true)
        //TODO: odtworzenie dźwięku
        if (timerEndTime > 0L) {
            FabToast.makeText(context, context.resources.getString(R.string.timer_end),
                    FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_CENTER).show()
        }
    }

    override fun resetTimer() {
        //TODO: resetowanie Timera
    }

    override fun convertTime(minutes: Long, seconds: Long): Long {
        return TimeUnit.MINUTES.toMillis(minutes) +
                TimeUnit.SECONDS.toMillis(seconds)
    }

    private val updateTimerThread = object : Runnable {
        override fun run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime
            updatedTime = timeTemp + timeInMilliseconds

            var seconds = (pickedSec - ( updatedTime / 1000)).toInt()
            val minutes = seconds / 60
            seconds %= 60

            var string = ""
            string += "" + String.format("%02d", minutes)
            string += ":" + String.format("%02d", seconds)

            if (updatedTime > timerEndTime) {
                stopTimer(false)
            } else {
                view.setTimerValue(string)
                customHandler.postDelayed(this, 0)
            }
        }
    }
}