package pl.perski.lukasz.marathon.ui.act.fragmentContainer

import android.content.Context
import pl.perski.lukasz.marathon.ui.act.training.TrainingActivityMVP

class FragmentContainerActMVP {
    interface View {
        fun getContext(): Context

    }

    interface Presenter {
        fun setView(view: FragmentContainerActMVP.View)
        fun showFragment(fragmentToShow : String)
    }
}