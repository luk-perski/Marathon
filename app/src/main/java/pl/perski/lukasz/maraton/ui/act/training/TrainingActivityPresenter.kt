package pl.perski.lukasz.maraton.ui.act.training

import android.support.v4.app.FragmentManager
import android.util.Log
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData
import pl.perski.lukasz.maraton.ui.fragments.exerciseFragments.ExerciseBaseFragment
import pl.perski.lukasz.maraton.utils.FragmentUtils


class TrainingActivityPresenter(var manager : FragmentManager) : TrainingActivityMVP.Presenter {

    var model = TrainingModel()
    private lateinit var view: TrainingActivityMVP.View
    lateinit var exercisesList: List<ExerciseData>
    var counter = 0
    lateinit var exercise: ExerciseData
    var countOfExercises: Int = -1
    var fragment: ExerciseBaseFragment? = null
    var exerciseDoneList = ArrayList<ExerciseDoneData>()

    override fun setView(view: TrainingActivityMVP.View) {
        this.view = view
    }

    override fun startTraining() {
        getMorningExercises()
        displayFragment()
    }

    fun displayFragment() {
        getDataFromFragment()
        exercise = exercisesList.get(counter)
        fragment = ExerciseBaseFragment.newInstance(exercise)
        FragmentUtils.replaceFragmentToActivity(manager, R.id.fragment_exercise_container, fragment!!)
        setControls()
    }

    private fun getDataFromFragment() {
        if (fragment != null) {
            exerciseDoneList.add(fragment!!.getData())
        }
    }

    override fun setControls() {
        ++counter
        view.setToolbarTittle(exercise.title, "$counter/$countOfExercises")

        if (counter >= countOfExercises) {
            view.lockNextBtn()
            view.unlockEndBtn()
        }

        if (exercise.exerciseTypeId != 6) {
            view.setExercisePicker(1F)
            view.setAmountQuestion(view.getContext().getString(R.string.amount_question))

        } else {
            view.setExercisePicker(30F)
            view.setAmountQuestion(view.getContext().getString(R.string.time_question))
        }
    }

    fun endTraining() {
        getDataFromFragment()
        model.saveToDB(exerciseDoneList)
    }

    override fun getMorningExercises() {

        try {
            exercisesList = model.getExercisesFromDB(view.getContext())
            countOfExercises = exercisesList.count()
        } catch (e: Exception) {
            //TODO: ogarnij try/catche
        }
    }

}