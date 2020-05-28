package pl.perski.lukasz.maraton.ui.fragments.exerciseFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_exercise_type_three.*
import kotlinx.android.synthetic.main.fragment_exercise_type_three.view.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData
import pl.perski.lukasz.maraton.data.repositories.RhymesRepository

class ExerciseFragmentTypeThree : ExerciseBaseFragment() {

    private lateinit var root: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_exercise_type_three, container, false)
        getDataFromArg(this)
        setControls()
        return root
    }

    override fun getData(isDone: Boolean, repeatAmount: Int?, timeAmount: Int?): ExerciseDoneData {
        return ExerciseDoneData(
                exercise!!.recId,
                exercise!!.title,
                exercise!!.exerciseGroupId,
                exercise!!.exerciseTypeId,
                isDone,
                repeatAmount,
                timeAmount,
                pickerMaxRepeat.value.let { pickerMaxRepeat.value.toInt() },
                null
        )
    }

    fun setControls() {
        val repository = RhymesRepository(activity!!.applicationContext)
        root.tvRhyme.text = repository.getRhymes(exercise!!.recId)
    }
}

