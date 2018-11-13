package pl.perski.lukasz.marathon.ui.act.training

import android.support.v4.app.FragmentManager
import pl.perski.lukasz.marathon.R
import pl.perski.lukasz.marathon.data.model.ExerciseData
import pl.perski.lukasz.marathon.ui.exerciseFragments.ExerciseBaseFragment
import pl.perski.lukasz.marathon.utils.FragmentUtils


class TrainingActivityPresenter(var manager : FragmentManager) : TrainingActivityMVP.Presenter {

    var model = TrainingModel()
    private lateinit var view: TrainingActivityMVP.View
    lateinit var exercisesList: List<ExerciseData>
    var counter = 0
    lateinit var exercise: ExerciseData
    var countOfExercises: Int = -1
    var fragment: ExerciseBaseFragment? = null


    override fun setView(view: TrainingActivityMVP.View) {
        this.view = view
    }

    override fun startTraining() {
        getMorningExercises()
        showFragment()
    }

    fun showFragment() {
        exercise = exercisesList.get(counter)
        fragment = ExerciseBaseFragment.newInstance(exercise)
        FragmentUtils.replaceFragmentToActivity(manager, R.id.fragment_exercise_container, fragment!!)
       ++counter
      setControls()
    }

    override fun setControls() {
        view.setToolbarTittle(exercise.title, "$counter/$countOfExercises")
        if (counter >= countOfExercises) {
            view.lockNextBtn()
        }

        if (exercise.exerciseTypeId != 6){
            view.setExercisePicker(1F)
            view.setAmountQuestion(view.getContext().getString(R.string.amount_question))

        } else {
            view.setExercisePicker(30F)
            view.setAmountQuestion(view.getContext().getString(R.string.time_question))
        }
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