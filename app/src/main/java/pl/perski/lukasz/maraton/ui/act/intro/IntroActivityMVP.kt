package pl.perski.lukasz.maraton.ui.act.intro

import android.content.Context

interface IntroActivityMVP {
    interface View {
        fun getContext(): Context
        fun finishActivity()
    }

    interface Presenter {
        fun setView(view: IntroActivityMVP.View)
        fun grantPermissions()
    }

    interface Model {
        fun copyDB(context: Context)
    }
}