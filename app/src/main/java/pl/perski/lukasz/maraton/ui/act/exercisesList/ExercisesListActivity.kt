package pl.perski.lukasz.maraton.ui.act.exercisesList

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_exercises_list.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.adapters.CustomExpandableListAdapter

class ExercisesListActivity : AppCompatActivity(), ExercisesListActivityMVP.View {

    var presenter = ExercisesActivityPresenter()
    private val buttonClick = AlphaAnimation(1f, 0.8f)
    private lateinit var adapter: CustomExpandableListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises_list)
        presenter.setView(this)
        presenter.setExpandableLV()
        title = getString(R.string.actExerciseList_title)

        btnDefineExercises.setOnClickListener {
            btnDefineExercises.startAnimation(buttonClick)
            presenter.addCustomTraining()
        }
    }

    override fun getContext(): Context {
        return this
    }

    override fun setExercises(header: MutableList<String>, body: MutableList<MutableList<String>>) {
        adapter = CustomExpandableListAdapter(this, expandLvExercises, header, body)
        expandLvExercises.setAdapter(adapter)
    }

    override fun notifyDataSetChanged() {
//TODO:
    }
}