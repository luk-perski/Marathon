package pl.perski.lukasz.marathon.act.main

import android.content.Context

interface MainActivityMVP {

    interface View{

        fun getContext(): Context
    }

    interface Presenter {
        fun setView(view: MainActivityMVP.View)
        //TODO: kopia db i sprawdzenie uprawnienień podczas splash screenu
//fun onFirstLunch()
        fun copyDB()
        fun grantPermissions()
    }

    interface Model
    {
        fun copyDB(context : Context)
    }

}