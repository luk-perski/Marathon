package pl.perski.lukasz.marathon.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.perski.lukasz.marathon.R
import pl.perski.lukasz.marathon.data.model.ExerciseData
import java.io.Serializable

class ExerciseFragmentTypeOne:  ExerciseBaseFragment() {

    private val EXERCISE = "exercise"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_exercise_type_one,container,false)
    }






}