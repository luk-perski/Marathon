package pl.perski.lukasz.marathon.ui.act.fragmentContainer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import pl.perski.lukasz.marathon.R
import pl.perski.lukasz.marathon.ui.act.stopwatch.StopwatchFragment
import pl.perski.lukasz.marathon.utils.FragmentUtils

class FragmentContainerActPresenter(var manager : FragmentManager)  :FragmentContainerActMVP.Presenter{

val STOPWATCH = "stopwatch"
    var fragment: Fragment? = null

    override fun setView(view: FragmentContainerActMVP.View) {

    }
    override fun showFragment(fragmentToShow : String) {
        when (fragmentToShow){
            STOPWATCH -> fragment = StopwatchFragment()
        }
        FragmentUtils.replaceFragmentToActivity(manager, R.id.fragment_container, fragment!!)
    }



}