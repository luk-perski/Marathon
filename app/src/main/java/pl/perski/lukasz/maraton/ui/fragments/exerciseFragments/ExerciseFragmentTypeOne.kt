package pl.perski.lukasz.maraton.ui.fragments.exerciseFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData

class ExerciseFragmentTypeOne : ExerciseBaseFragment() {

    override fun getData(isDone: Boolean, repeatAmount: Int?, timeAmount: Int?): ExerciseDoneData {
        return ExerciseDoneData(
                exercise!!.recId,
                exercise!!.title,
                exercise!!.exerciseGroupId,
                exercise!!.exerciseTypeId,
                isDone,
                repeatAmount,
                timeAmount,
                null,
                null
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_exercise_type_one, container, false)
        getDataFromArg(this)
        return root
    }

}