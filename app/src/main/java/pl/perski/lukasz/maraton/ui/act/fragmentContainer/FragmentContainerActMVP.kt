package pl.perski.lukasz.maraton.ui.act.fragmentContainer

import android.content.Context
import android.content.Intent

class FragmentContainerActMVP {
    interface View {
        fun getContext(): Context
        fun setBtnEndExercise(state: Boolean)
    }

    interface Presenter {
        fun setView(view: View)
        fun showFragment(intent: Intent)
    }
}