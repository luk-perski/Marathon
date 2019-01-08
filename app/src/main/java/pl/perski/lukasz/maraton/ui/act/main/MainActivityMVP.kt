package pl.perski.lukasz.maraton.ui.act.main

import android.content.Context

interface MainActivityMVP {

    interface View{

        fun getContext(): Context
        fun startIntroActivity()
        fun startTraining(exercisesTitles : Array<String>, mode : Int)
        fun reloadActivity()
    }

    interface Presenter {
        fun setView(view: View)
        fun onFirstLaunch()
        fun MorningTraining()
        fun EveningTraining()
        fun chooser(mode : Int)
    }

    interface Model
    {}
}