package pl.perski.lukasz.maraton.ui.act.exercisesList

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.data.enums.ExerciseGroup
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.repositories.ExercisesRepository
import pl.perski.lukasz.maraton.utils.SharedPrefHelper
import spencerstudios.com.fab_toast.FabToast

//TODO : porządek - porobić funkjce, poprzenosić co trzeba do modelu
class ExercisesActivityPresenter : ExercisesListActivityMVP.Presenter {

    private lateinit var customTrainingTitle: String
    private lateinit var view: ExercisesListActivityMVP.View
    private lateinit var context: Context
    private var customTrainingString = String()
    private var groupsCodes: MutableList<Int> = ArrayList()
    private val STRING_SEPARATOR = ";"
    private var customTrainingExerciesSet = mutableSetOf<String>()
    private var titles: MutableList<MutableList<String>> = ArrayList()
    lateinit var sharedPrefHelper: SharedPrefHelper
    var model = ExercisesListModel()
    var groups: MutableList<String> = ArrayList()
    var customTrainingNames: MutableList<String>? = ArrayList()


    override fun setView(view: ExercisesListActivityMVP.View) {
        this.view = view
        context = view.getContext()
        sharedPrefHelper = SharedPrefHelper(context)
    }

    override fun addCustomTraining() {
        showInputDialog()
    }


    override fun getExerciseTitlesGroup(id: Int): MutableList<String> {
        return model.getExerciseTitlesGroupFromDB(view.getContext(), id)!!
    }

    fun setExpandableLV() {
        getExercises()
        groupsCodes.forEach {
            groups.add(ExerciseGroup.getByCode(it))
        }
        if (customTrainingNames != null) {
            customTrainingNames!!.forEach {
                groups.add(it)
            }
        }
        view.setExercises(groups, titles)
    }

    override fun getExercisesGroup(id: Int): MutableList<ExerciseData> {
        return model.getExercisesGroupFromDB(view.getContext(), id)!!
    }

    override fun getExercisesGroupId(): MutableList<Int> {
        //TODO: add catching null
        return model.getExercisesGroupIdFromDB(view.getContext())!!
    }

    override fun getExercises() {
        groupsCodes = getExercisesGroupId()
        groupsCodes.forEach { it ->
            titles.add(getExerciseTitlesGroup(it))
        }

        if (sharedPrefHelper.preferences.contains(SharedPrefHelper.CUSTOM_TRAINING_NAMES)) {
            customTrainingNames = sharedPrefHelper.customTrainingNames!!.toMutableList()
            customTrainingExerciesSet = sharedPrefHelper.customTrainingExerciesSet!!
            customTrainingExerciesSet.forEach {
                val exercisesFromString = it.split(STRING_SEPARATOR).toMutableList()
                titles.add(exercisesFromString)
            }
        }
    }

    private fun showChooserDialog() {
        val repository = ExercisesRepository(context)
        val items = repository.getExercises().map { it.toString() }.toTypedArray()
        val selectedList = java.util.ArrayList<Int>()
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.DialogTheme))
        val selectedStrings = java.util.ArrayList<String>()

        builder.setTitle(R.string.chose_exercises)
        builder.setMultiChoiceItems(items, null
        ) { _, which, isChecked ->
            if (isChecked) {
                selectedList.add(which)
            } else if (selectedList.contains(which)) {
                selectedList.remove(Integer.valueOf(which))
            }
        }
        builder.setPositiveButton(R.string.ok) { _, _ ->
            if (selectedList.isNotEmpty()) {
                for (j in selectedList.indices) {
                    selectedStrings.add(items[selectedList[j]])
                    customTrainingString += if (j == 0) {
                        items[selectedList[j]]
                    } else {
                        (STRING_SEPARATOR + items[selectedList[j]])
                    }
                }
                customTrainingNames?.add(customTrainingTitle)
                customTrainingExerciesSet.add(customTrainingString)
                sharedPrefHelper.customTrainingNames = customTrainingNames!!.toMutableSet()
                sharedPrefHelper.customTrainingExerciesSet = customTrainingExerciesSet
            } else {
                FabToast.makeText(context, context.resources.getString(R.string.no_exercises_chosen),
                        FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_TOP).show()
                builder.show()
            }
        }
                .setNegativeButton(R.string.cancel, null)
        builder.show()
    }

    private fun showInputDialog() {
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.DialogTheme))
        val inflater = LayoutInflater.from(context)
        builder.setTitle(context.resources.getString(R.string.chooseTitle))
        val dialogLayout = inflater.inflate(R.layout.alert_dialog_edit_text, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        builder.setPositiveButton(R.string.next_step) { _, _ ->
            customTrainingTitle = editText.text.toString()
            showChooserDialog()
        }
        builder.show()
    }
}

