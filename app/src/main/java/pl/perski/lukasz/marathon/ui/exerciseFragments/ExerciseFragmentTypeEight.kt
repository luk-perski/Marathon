package pl.perski.lukasz.marathon.ui.exerciseFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_exercise_type_one.view.*
import pl.perski.lukasz.marathon.R
import pl.perski.lukasz.marathon.ui.act.stopwatch.StopwatchFragment


class ExerciseFragmentTypeEight : ExerciseBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_exercise_type_eight, container, false)
        getDataFromArg(this)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val childFragment = StopwatchFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.child_fragment_container, childFragment).commit()
    }

}