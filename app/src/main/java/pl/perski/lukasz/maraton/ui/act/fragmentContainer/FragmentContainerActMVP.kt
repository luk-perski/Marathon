package pl.perski.lukasz.maraton.ui.act.fragmentContainer

import android.content.Context
import android.content.Intent
import pl.perski.lukasz.maraton.data.model.ExerciseData

class FragmentContainerActMVP {
    interface View {
        fun getContext(): Context
        fun setBtnEndExercise(state : Boolean)
    }

    interface Presenter {
        fun setView(view: FragmentContainerActMVP.View)
        fun showFragment(intent : Intent)
    }
}