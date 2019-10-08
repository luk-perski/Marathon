package pl.perski.lukasz.maraton.ui.fragments.stopwatch

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.perski.lukasz.maraton.R
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.fragment_stopwatch.view.*

class StopwatchFragment : Fragment(), StopwatchFragmentMVP.View {

    lateinit var currentView: View
    private val buttonClick = AlphaAnimation(1f, 0.8f)
    var presenter = StopwatchFragmentPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        currentView = inflater.inflate(R.layout.fragment_stopwatch, container, false)
        presenter.setView(this)
        setEvents()
        return currentView
    }

    override fun changeResetColor(state: Boolean) {
        if (state) {
            currentView.btnResetStopwatch.background = currentView.context.getDrawable(R.drawable.bg_round_30dp_primary)
        } else {
            currentView.btnResetStopwatch.background = currentView.context.getDrawable(R.drawable.bg_round_30dp_primary_dark)
        }

    }

    override fun changeStartColor(state: Boolean) {
        if (state) {
            currentView.btnStartStopwatch.background = currentView.context.getDrawable(R.drawable.bg_round_30dp_primary)
        } else {
            currentView.btnStartStopwatch.background = currentView.context.getDrawable(R.drawable.bg_round_30dp_primary_dark)
        }
    }

    override fun changeStopColor(state: Boolean) {
        if (state) {
            currentView.btnStopStopwatch.background = currentView.context.getDrawable(R.drawable.bg_round_30dp_primary)
        } else {
            currentView.btnStopStopwatch.background = currentView.context.getDrawable(R.drawable.bg_round_30dp_primary_dark)
        }
    }

    override fun changeResetState(state: Boolean) {
        currentView.btnResetStopwatch.isClickable = state
    }

    override fun changeStartState(state: Boolean) {
        currentView.btnStartStopwatch.isClickable = state
    }

    override fun changeStopState(state: Boolean) {
        currentView.btnStopStopwatch.isClickable = state
    }

    override fun setTimerValue(value: String) {
        currentView.stopwatchValue.text = value
    }

    override fun getContext(): Context {
        return activity!!.applicationContext
    }

    override fun setToolbarTittle(title: String, subtitle: String) {
        (activity as AppCompatActivity).supportActionBar!!.subtitle = subtitle
        (activity as AppCompatActivity).supportActionBar!!.title = title
    }

    override fun setEvents() {
        currentView.btnStartStopwatch.setOnClickListener {
            currentView.btnStartStopwatch.startAnimation(buttonClick)
            presenter.startStopwatch()
        }
        currentView.btnResetStopwatch.setOnClickListener {
            currentView.btnResetStopwatch.startAnimation(buttonClick)
            presenter.resetStopwatch()
        }
        currentView.btnStopStopwatch.setOnClickListener {
            currentView.btnStopStopwatch.startAnimation(buttonClick)
            presenter.stopStopwatch()
        }
    }
}

