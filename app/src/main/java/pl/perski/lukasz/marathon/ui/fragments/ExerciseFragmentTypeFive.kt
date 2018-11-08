package pl.perski.lukasz.marathon.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_exercise_type_one.view.*
import pl.perski.lukasz.marathon.R

class ExerciseFragmentTypeFive : ExerciseBaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root =  inflater.inflate(R.layout.fragment_exercise_type_five,container,false)
        getDataFromArg(this)
        root.tvTitle.text = "Fragment nr ${exercise?.exerciseTypeId}"
        return root
    }
}