package pl.perski.lukasz.marathon.ui.act.exercises

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_exercises_list.*
import pl.perski.lukasz.marathon.R
import pl.perski.lukasz.marathon.data.model.ExerciseData

class ExercisesActivity : AppCompatActivity(), ExercisesActivityMVP.View {


    var presenter = ExercisesActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises_list)
        presenter.setView(this)
        presenter.getExercises()
    }

    override fun getContext(): Context {
        return this
    }

    override fun setExercises(exercisesList : List<ExerciseData>) {
        lvExercises.adapter =  ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, exercisesList)
    }


}