package pl.perski.lukasz.maraton.ui.act.fragmentContainer

import android.content.Context

class FragmentContainerActMVP {
    interface View {
        fun getContext(): Context

    }

    interface Presenter {
        fun setView(view: FragmentContainerActMVP.View)
        fun showFragment(fragmentToShow : String)
    }
}