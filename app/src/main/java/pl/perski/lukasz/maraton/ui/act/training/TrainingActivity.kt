package pl.perski.lukasz.maraton.ui.act.training


import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_training.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.adapters.FragmentDialog
import pl.perski.lukasz.maraton.ui.act.exercisesList.ExercisesListActivity
import pl.perski.lukasz.maraton.ui.act.main.MainActivity

class TrainingActivity : AppCompatActivity(), TrainingActivityMVP.View
{
    override fun lockSkipBtn() {
        btnSkip.visibility = View.INVISIBLE
    }


    override fun getExerciseAmount(): Int {return exercisePicker.value.toInt()}

    override fun setExerciseTitle(exerciseTitle: String) {
        tvTitle.text = exerciseTitle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
        presenter.setView(this)
        presenter.startTraining(intent)
        setEvents()
    }

    override fun getContext(): Context {
        return this
    }

    override fun unlockEndBtn() {
        btnEndTraining.visibility = View.VISIBLE
        btnEndTraining.isClickable = true
    }

    override fun setEvents() {
        btnNextFragment.setOnClickListener {
            btnNextFragment.startAnimation(buttonClick)
           presenter.displayFragment(true)
        }

        btnEndTraining.setOnClickListener {
            btnNextFragment.startAnimation(buttonClick)
            presenter.endTraining()
            //TODO: Przejście gdzieś indziej
            btnEndTraining.isClickable = false
        }

        btnSkip.setOnClickListener {
            btnSkip.startAnimation(buttonClick)
            presenter.skipExercise()
        }
    }

    override fun setExercisePicker(value: Float) {
        exercisePicker.setPickerValue(value)
    }

    var presenter = TrainingActivityPresenter(supportFragmentManager)
    private val buttonClick = AlphaAnimation(1f, 0.8f)

    override fun setToolbarTittle(title: String) {
        supportActionBar?.title = title
    }

    override fun lockNextBtn() {
        btnNextFragment.isClickable = false
        btnNextFragment.visibility = View.INVISIBLE
    }

    override fun setAmountQuestion(question: String) {
        tvAmount_Qestion.text = question
    }



    override fun onBackPressed() {
presenter.showAlertDialog()
    }


    override fun finishAct()
    {
        finish()
    }

fun saveDataAndFinish(mood : Int)
{
    presenter.saveDataAndFinish(mood)
}

}

