package pl.perski.lukasz.maraton.ui.fragments.timer


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_timer.view.*
import pl.perski.lukasz.maraton.R

class TimerFragment : Fragment(), TimerFragmentMVP.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        currentView = inflater.inflate(R.layout.fragment_timer, container, false)
        presenter.setView(this)
        setEvents()
        return currentView
    }

    override fun getMinuteValue(): Long {
        return java.lang.Long.parseLong(currentView.minutePicker.getCurrentItem())
    }

    override fun getSecondsValue(): Long {
        return java.lang.Long.parseLong(currentView.secondsPicker.getCurrentItem())
    }

    override fun setTimerValue(value: String) {
        currentView.timerValue.text = value
    }

    override fun changeStartColor(state: Boolean) {
        if (state) {
            currentView.btnStartTimer.background = currentView.context.getDrawable(R.drawable.bg_round_30dp_primary)
        } else {
            currentView.btnStartTimer.background = currentView.context.getDrawable(R.drawable.bg_round_30dp_primary_dark)
        }
    }

    override fun changeStartState(state: Boolean) {
        currentView.btnStartTimer.isClickable = state
    }

    override fun getContext(): Context {
        return activity!!.applicationContext
    }

    override fun setToolbarTittle(title: String, subtitle: String) {
        (activity as AppCompatActivity).supportActionBar!!.subtitle = subtitle
        (activity as AppCompatActivity).supportActionBar!!.title = title
    }

    override fun setEvents() {
        currentView.btnStartTimer.setOnClickListener {
            currentView.btnStartTimer.startAnimation(buttonClick)
            presenter.startTimer()
        }
    }

    lateinit var currentView: View
    private val buttonClick = AlphaAnimation(1f, 0.8f)
    var presenter = TimerFragmentPresenter()

}
