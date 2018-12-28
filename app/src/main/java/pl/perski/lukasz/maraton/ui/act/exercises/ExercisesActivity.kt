package pl.perski.lukasz.maraton.ui.act.exercises

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ExpandableListAdapter
import kotlinx.android.synthetic.main.activity_exercises_list.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.adapters.CustomExpandableListAdapter
import pl.perski.lukasz.maraton.data.model.ExerciseData

class ExercisesActivity : AppCompatActivity(), ExercisesActivityMVP.View {

    var presenter = ExercisesActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises_list)
        presenter.setView(this)
        presenter.setExpandableLV()
        title = getString(R.string.exercise_title) //TODO: Przenieś to, jeżeli działa
    }

    override fun getContext(): Context {
        return this
    }

    override fun setExercises(header: MutableList<String>, body: MutableList<MutableList<String>>) {
        expandLvExercises.setAdapter(CustomExpandableListAdapter(this, expandLvExercises, header, body))
    }


}