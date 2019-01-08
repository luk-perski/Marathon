package pl.perski.lukasz.maraton.ui.fragments.exerciseFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_exercise_type_three.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData


class ExerciseFragmentTypeThree : ExerciseBaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_exercise_type_three, container, false)
        getDataFromArg(this)
        return root
    }


    override fun getData(isDone : Boolean, repeatAmount : Int?, timeAmount: Int?): ExerciseDoneData {
        return ExerciseDoneData(
                exercise!!.recId,
                exercise!!.title,
                exercise!!.exerciseGroupId,
                exercise!!.exerciseTypeId,
                isDone,
                repeatAmount,
                timeAmount,
                pickerMaxRep.value.toInt(),
                null
        )
    }
}

