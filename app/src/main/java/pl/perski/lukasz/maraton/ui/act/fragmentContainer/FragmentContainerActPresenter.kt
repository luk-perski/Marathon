package pl.perski.lukasz.maraton.ui.act.fragmentContainer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.ui.fragments.calendar.CalendarFragment
import pl.perski.lukasz.maraton.ui.fragments.stopwatch.StopwatchFragment
import pl.perski.lukasz.maraton.ui.fragments.timer.TimerFragment
import pl.perski.lukasz.maraton.ui.fragments.voiceRecorder.VoiceRecFragment
import pl.perski.lukasz.maraton.utils.FragmentUtils

class FragmentContainerActPresenter(var manager : FragmentManager)  :FragmentContainerActMVP.Presenter{

    val STOPWATCH = "stopwatch"
    val TIMER = "timer"
    val RECORDS = "records"
    val CALENDAR = "calendar"
    var fragment: Fragment? = null

    override fun setView(view: FragmentContainerActMVP.View) {

    }
    override fun showFragment(fragmentToShow : String) {
        when (fragmentToShow){
            STOPWATCH -> fragment = StopwatchFragment()
            TIMER -> fragment = TimerFragment()
            RECORDS -> fragment = VoiceRecFragment()
            CALENDAR -> fragment = CalendarFragment()
        }
        FragmentUtils.replaceFragmentToActivity(manager, R.id.fragment_container, fragment!!)
    }



}