package pl.perski.lukasz.maraton.ui.fragments.exerciseFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_exercise_type_five.view.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData
import pl.perski.lukasz.maraton.data.repositories.HintsRepository

class ExerciseFragmentTypeFive : ExerciseBaseFragment() {


    private lateinit var root : View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root =  inflater.inflate(R.layout.fragment_exercise_type_five,container,false)
        getDataFromArg(this)
        setControls()
        return root
    }

    override fun getData(): ExerciseDoneData {
        return ExerciseDoneData(
                exercise!!.recId,
                exercise!!.title,
                exercise!!.exerciseGroupId,
                exercise!!.exerciseTypeId,
                false,
                null,
                null,
                null,
                null
        )
    }

    fun setControls()
    {
        val repository = HintsRepository(activity!!.applicationContext)
        root.lvHints.adapter = ArrayAdapter(
                activity!!.applicationContext ,
                R.layout.list_item,
                repository.getAll(exercise!!.recId))

    }
}