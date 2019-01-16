package pl.perski.lukasz.maraton.ui.act.main

import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.view.ContextThemeWrapper
import com.google.firebase.auth.FirebaseAuth
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.adapters.SharedPrefHelper
import pl.perski.lukasz.maraton.data.repositories.ExercisesRepository
import pl.perski.lukasz.maraton.ui.act.login.LoginActivity
import pl.perski.lukasz.maraton.ui.act.training.TrainingActivity
import pl.perski.lukasz.maraton.utils.CONST_STRINGS
import spencerstudios.com.fab_toast.FabToast
import java.util.*

class MainActivityPresenter : MainActivityMVP.Presenter {

    var model = MainModel()
    private lateinit var view: MainActivityMVP.View
    private lateinit var context: Context
    lateinit var sharedPrefHelper: SharedPrefHelper
    lateinit var exercisesMorningTitles: Array<String>
    lateinit var exercisesEveningTitles: Array<String>
    private lateinit var auth: FirebaseAuth

    override fun setView(view: MainActivityMVP.View) {
        this.view = view
        context = view.getContext()
        sharedPrefHelper = SharedPrefHelper(context)
        auth = FirebaseAuth.getInstance()
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
            if (selectedList.isNotEmpty()) {
            for (j in selectedList.indices) {
                selectedStrings.add(items[selectedList[j]])
            }

                when (mode) {
                    1 -> {
                        exercisesMorningTitles = selectedStrings.toTypedArray()
                        startTraining(exercisesMorningTitles, mode)
                    }
                    2 -> {
                        exercisesEveningTitles = selectedStrings.toTypedArray()
                        startTraining(exercisesEveningTitles, mode)
                    }
                    3, 4 -> {
                        view.reloadActivity()
                    }
                }
                model.saveChoice(selectedStrings, mode, sharedPrefHelper)
            } else {
                FabToast.makeText(context, context.resources.getString(R.string.no_exercises_chosen),
                        FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_TOP).show()
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

    override fun morningTraining() {
        if (sharedPrefHelper.firstMorning && !sharedPrefHelper.checkIfExists(SharedPrefHelper.MORNING_EXERCISES)) {
            showInfoDialog(context.resources.getString(R.string.atMorning), 1)

        } else {
            startTraining(sharedPrefHelper.morningExercises.toTypedArray(), 1)
        }
    }

    override fun eveningTraining() {
        if (sharedPrefHelper.firstEvening && !sharedPrefHelper.checkIfExists(SharedPrefHelper.EVENING_EXERCISES)) {
            showInfoDialog(context.resources.getString(R.string.atEvening), 2)
        } else {
            startTraining(sharedPrefHelper.eveningExercises.toTypedArray(), 2)
        }
    }

    override fun onFirstLaunch() {
        if (sharedPrefHelper.firstLaunch) {
            view.startIntroActivity()
        }
    }

    override fun checkAuth(): Boolean {
        return auth.currentUser != null
    }

    override fun logoutUser() {
        auth.signOut()
        FabToast.makeText(context, context.resources.getString(R.string.logout_successful),
                FabToast.LENGTH_LONG, FabToast.INFORMATION, FabToast.POSITION_CENTER).show()
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
        view.finishAct()
    }

    private fun startTraining(exercisesTitles: Array<String>, mode: Int) {
        val intent = Intent(context, TrainingActivity::class.java)
        intent.putExtra(CONST_STRINGS.TRAINING_ENTER_DATA, exercisesTitles)
        intent.putExtra(CONST_STRINGS.TRAINING_END, mode)
        context.startActivity(intent)
        view.finishAct()
    }
}



