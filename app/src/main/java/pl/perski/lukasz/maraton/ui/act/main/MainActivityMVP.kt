package pl.perski.lukasz.maraton.ui.act.main

import android.content.Context
import pl.perski.lukasz.maraton.utils.SharedPrefHelper
import java.util.*

interface MainActivityMVP {

    interface View {

        fun getContext(): Context
        fun startIntroActivity()
        fun reloadActivity()
        fun finishAct()
    }

    interface Presenter {
        fun setView(view: View)
        fun onFirstLaunch()
        fun morningTraining()
        fun eveningTraining()
        fun chooser(mode: Int)
        fun checkAuth(): Boolean
        fun logoutUser()
    }

    interface Model {
        fun saveChoice(titles: ArrayList<String>, mode: Int, sharedPrefHelper: SharedPrefHelper)
    }
}