package pl.perski.lukasz.marathon.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.perski.lukasz.marathon.R
import pl.perski.lukasz.marathon.data.model.ExerciseData
import java.io.Serializable

open class ExerciseBaseFragment : Fragment(), ExerciseFragmentMVP.View {


    private val EXERCISE = "exercise"

    fun newInstance(exercise: ExerciseData): ExerciseBaseFragment {
        val args = Bundle()
        args.putSerializable(EXERCISE, exercise as Serializable)
        val fragment = ExerciseBaseFragment()
        fragment.arguments = args
        return fragment

    }


}