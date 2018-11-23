package pl.perski.lukasz.maraton.ui.fragments.calendar

import android.content.Context

interface CalendarFragmentMVP {
    interface View {
        fun getContext(): Context
        fun setToolbarTittle(title : String, subtitle: String)
        fun setEvents()
    }

    interface Presenter {
        fun setView(view: View)
        fun setControls()
    }

    interface Model
    {}
}