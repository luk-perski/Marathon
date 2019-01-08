package pl.perski.lukasz.maraton.ui.act.main

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.view.ContextThemeWrapper
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.adapters.SharedPrefHelper
import pl.perski.lukasz.maraton.data.repositories.ExercisesRepository
import spencerstudios.com.fab_toast.FabToast
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

    override fun chooser(mode: Int) {
        val repository = ExercisesRepository(context)
        val items = repository.getExercises().map { it.toString() }.toTypedArray()
        val selectedList = ArrayList<Int>()
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.DialogTheme))
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
            if (selectedStrings.isNotEmpty()) {
                when (mode) {
                    0 -> {
                        sharedPrefHelper.morningExercises = selectedStrings.toMutableSet()
                        exercisesMorningTitles = selectedStrings.toTypedArray()
                        sharedPrefHelper.firstMorning = false
                        view.startTraining(exercisesMorningTitles, 1)
                    }
                    1 -> {
                        sharedPrefHelper.eveningExercises = selectedStrings.toMutableSet()
                        exercisesEveningTitles = selectedStrings.toTypedArray()
                        sharedPrefHelper.firstEvening = false
                        view.startTraining(exercisesEveningTitles, 2)
                    }
                    2 -> {
                        sharedPrefHelper.morningExercises = selectedStrings.toMutableSet()
                        view.reloadActivity()
                    }
                    3 -> {
                        sharedPrefHelper.eveningExercises = selectedStrings.toMutableSet()
                        view.reloadActivity()
                    }
                }
            } else {
                FabToast.makeText(context, context.resources.getString(R.string.no_exercises_chosen), FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_TOP).show()

                builder.show()
            }
        }
        builder.show()
    }

    private fun showInfoDialog(timeOfTheDay: String, mode: Int) {
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.DialogTheme))

        with(builder)
        {
            setTitle(context.resources.getString(R.string.lets_begin))
            setMessage(context.resources.getString(R.string.chooser_info_message) + " " + timeOfTheDay)
            setPositiveButton(context.resources.getString(R.string.ok)) { dialogInterface, i ->
                run {
                    chooser(mode)
                }
            }
            show()
        }
    }

    //TODO: alert dialog, że można kiedyś zmienić
    override fun MorningTraining() {
        if (sharedPrefHelper.firstMorning) {
            showInfoDialog(context.resources.getString(R.string.atMorning), 0)

        } else {
            view.startTraining(sharedPrefHelper.morningExercises.toTypedArray(), 0)
        }
    }

    override fun EveningTraining() {
        if (sharedPrefHelper.firstEvening) {
            showInfoDialog(context.resources.getString(R.string.atEvening), 1)
        } else {
            view.startTraining(sharedPrefHelper.eveningExercises.toTypedArray(), 2)
        }
    }

    override fun onFirstLaunch() {
        if (sharedPrefHelper.firstLaunch) {
            view.startIntroActivity()
        }
    }
}



