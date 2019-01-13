package pl.perski.lukasz.maraton.ui.act.training

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import android.support.v7.view.ContextThemeWrapper
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.adapters.FragmentDialog
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData
import pl.perski.lukasz.maraton.ui.act.exercisesList.ExercisesListActivity
import pl.perski.lukasz.maraton.ui.act.main.MainActivity
import pl.perski.lukasz.maraton.ui.fragments.exerciseFragments.ExerciseBaseFragment
import pl.perski.lukasz.maraton.utils.CONST_STRINGS
import pl.perski.lukasz.maraton.utils.FragmentUtils


class TrainingActivityPresenter(var manager : FragmentManager) : TrainingActivityMVP.Presenter {

    var model = TrainingModel()
    private lateinit var view: TrainingActivityMVP.View
    lateinit var exercisesList: List<ExerciseData>
    private lateinit var context: Context
    var counter = 0
    lateinit var exercise: ExerciseData
    var countOfExercises: Int = -1
    var fragment: ExerciseBaseFragment? = null
    var exerciseDoneList = ArrayList<ExerciseDoneData>()
    var trainingEndMode: Int = 0

    override fun setView(view: TrainingActivityMVP.View) {
        this.view = view
        context = view.getContext()
    }

    override fun startTraining(intent: Intent) {
        getExercises(intent)
        displayFragment(false)
    }

    fun displayFragment(isSkipped: Boolean) {
        if (fragment != null) {
            if (isSkipped) {
                getDataFromSkippedFragment()
            } else {
                getDataFromFragment()
            }
        }
        exercise = exercisesList.get(counter)
        fragment = ExerciseBaseFragment.newInstance(exercise)
        FragmentUtils.replaceFragmentToActivity(manager, R.id.fragment_exercise_container, fragment!!)
        setControls()
    }

    private fun getDataFromFragment() {

        if (exercise.exerciseTypeId == 6) {
            exerciseDoneList.add(fragment!!.getData(true, null, view.getExerciseAmount()))
        } else {
            exerciseDoneList.add(fragment!!.getData(true, view.getExerciseAmount(), null))
        }
    }

    private fun getDataFromSkippedFragment() {

        if (exercise.exerciseTypeId == 6) {
            exerciseDoneList.add(fragment!!.getData(false, null, 0))
        } else {
            exerciseDoneList.add(fragment!!.getData(false, 0, null))
        }
    }

    override fun showAlertDialog() {

        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.DialogTheme))
        if (trainingEndMode == 0) {
            with(builder)
            {
                setTitle(context.resources.getString(R.string.end_training_question))
                setPositiveButton(context.resources.getString(R.string.end)) { dialogInterface, i ->
                    run {
                        endTraining()
                    }
                }
                setNegativeButton(context.resources.getString(R.string.cancel), null)

                show()
            }
        } else {
            with(builder)
            {
                setTitle(context.resources.getString(R.string.end_and_save_training_question))
                //setMessage(context.resources.getString(R.string.chooser_info_message))
                setNegativeButton(context.resources.getString(R.string.save)) { dialogInterface, i ->
                    run {
                        endTraining()
                    }
                }
                setPositiveButton(context.resources.getString(R.string.only_end)) { dialogInterface, i ->
                    run {
                        trainingEndMode = 3
                        endTraining()
                    }
                }
                setNeutralButton(context.resources.getString(R.string.cancel), null)
                show()
            }
        }
    }

    override fun setControls() {
        ++counter
        view.setToolbarTittle("$counter/$countOfExercises")
        view.setExerciseTitle(exercise.title)

        if (counter >= countOfExercises) {
            view.lockNextBtn()
            view.lockSkipBtn()
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

        when (trainingEndMode) {
            0 -> {
                view.finishAct()
            }

            1 -> {
                saveDataAndFinish()
            }

            2 -> {
                showMoodDialog()
            }

            3 -> {
                startMainAct()
            }
        }
    }

    override fun getExercises(intent: Intent) {
        exercisesList = model.getExercisesFromDBByTitles(context, intent)!!
        trainingEndMode = model.getTrainingEnd(intent)
        countOfExercises = exercisesList.count()
    }

    fun startMainAct() {
        view.finishAct()
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    fun saveDataAndFinish() {
        getDataFromFragment()
        model.saveToDB(exerciseDoneList)
        view.finishAct()
        startMainAct()
    }

    fun saveDataAndFinish(mood: Int) {
        exerciseDoneList.add(ExerciseDoneData(-100, CONST_STRINGS.MOOD, mood))
        saveDataAndFinish()
    }


    fun showMoodDialog() {
        val ft = manager.beginTransaction()
        val newFragment = FragmentDialog.newInstance()
        newFragment.show(ft, "dialog")
    }
}