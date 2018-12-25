package pl.perski.lukasz.maraton.ui.act.training

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_training.*
import pl.perski.lukasz.maraton.R

class TrainingActivity : AppCompatActivity(), TrainingActivityMVP.View
{

    override fun unlockEndBtn() {
            btnEndTraining.visibility = View.VISIBLE
        btnEndTraining.isClickable = true
    }

    override fun setEvents() {
        btnNextFragment.setOnClickListener {
            btnNextFragment.startAnimation(buttonClick)
           presenter.displayFragment()
        }

        btnEndTraining.setOnClickListener {
            btnNextFragment.startAnimation(buttonClick)
            presenter.endTraining()
            //TODO: Przejście gdzieś indziej
            btnEndTraining.isClickable = false
        }
    }

    override fun setExercisePicker(value: Float) {
        exercisePicker.setPickerValue(value)
    }

    var presenter = TrainingActivityPresenter(supportFragmentManager)
    private val buttonClick = AlphaAnimation(1f, 0.8f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
        presenter.setView(this)
        presenter.startTraining()
        setEvents()
    }

    override fun getContext(): Context {
        return this
    }

    override fun onBackPressed() {
//        odkomentowane, jeżeli ma istnieć możliwość do poprzedniego fragmentu
//        val count = manager.backStackEntryCount
//        if (count <2) {
           this.finish()
//        } else {
//           manager.popBackStack()
//        }
    }

    override fun setToolbarTittle(title: String, subtitle: String) {
        supportActionBar?.title = title
        supportActionBar?.subtitle = subtitle
    }

    override fun lockNextBtn() {
        btnNextFragment.isClickable = false
        btnNextFragment.visibility = View.INVISIBLE
    }

    override fun setAmountQuestion(question: String) {
        tvAmount_Qestion.text = question
    }

}