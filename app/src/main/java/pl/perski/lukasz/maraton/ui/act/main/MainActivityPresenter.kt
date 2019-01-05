package pl.perski.lukasz.maraton.ui.act.main

import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.view.ContextThemeWrapper
import android.widget.Toast
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.adapters.SharedPrefHelper
import pl.perski.lukasz.maraton.data.repositories.ExercisesRepository
import java.util.*




class MainActivityPresenter : MainActivityMVP.Presenter {

    var model = MainModel()
    private lateinit var view: MainActivityMVP.View
    private lateinit var context: Context
    lateinit var sharedPrefHelper: SharedPrefHelper
    lateinit var exercisesMorningTitles: Array<String>
    lateinit var exercisesTitles: Array<String>
    lateinit var exercisesEveningTitles: Array<String>

    override fun setView(view: MainActivityMVP.View) {
        this.view = view
        context = view.getContext()
        sharedPrefHelper = SharedPrefHelper(context)
    }

override fun chooser(mode : Int)
{
    val repository = ExercisesRepository(context)
    val items = repository.getExercises().map { it.toString() }.toTypedArray()
    val selectedList = ArrayList<Int>()
    val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.AlertDialogCustom))
    val selectedStrings = ArrayList<String>()

    builder.setTitle(R.string.chose_exercises)
    builder.setMultiChoiceItems(items, null
    ) { dialog, which, isChecked ->
        if (isChecked) {
            selectedList.add(which)
        } else if (selectedList.contains(which)) {
            selectedList.remove(Integer.valueOf(which))
        }
    }
    builder.setPositiveButton(R.string.ok) { dialogInterface, i ->
        for (j in selectedList.indices) {
            selectedStrings.add(items[selectedList[j]])
        }

        when (mode) {
            0 -> {

            }
            1 -> {
                sharedPrefHelper.morningExercises = selectedStrings.toMutableSet()
                exercisesMorningTitles = selectedStrings.toTypedArray()
                sharedPrefHelper.firstMorning = false
                view.startTraining(exercisesMorningTitles)
            }
            2 -> {
                sharedPrefHelper.eveningExercises = selectedStrings.toMutableSet()
                exercisesEveningTitles = selectedStrings.toTypedArray()
                sharedPrefHelper.firstEvening = false
                view.startTraining(exercisesEveningTitles)
            }
            3 -> {
                sharedPrefHelper.morningExercises = selectedStrings.toMutableSet()
         view.reloadActivity()
            }
            4 -> {
                sharedPrefHelper.eveningExercises = selectedStrings.toMutableSet()
                view.reloadActivity()
            }
        }
    }
    builder.show()
}

    //TODO: alert dialog, że można kiedyś zmienić
    override fun MorningTraining() {
        if (sharedPrefHelper.firstMorning) {
            chooser(1)
        } else {
            view.startTraining(sharedPrefHelper.morningExercises.toTypedArray())
        }
    }

    override fun EveningTraining() {
        if (sharedPrefHelper.firstEvening) {
            chooser(2)
        } else {
            view.startTraining(sharedPrefHelper.eveningExercises.toTypedArray())
        }
    }

    override fun onFirstLaunch() {
        if (sharedPrefHelper.firstLaunch) {
            view.startIntroActivity()
        }
    }
}



