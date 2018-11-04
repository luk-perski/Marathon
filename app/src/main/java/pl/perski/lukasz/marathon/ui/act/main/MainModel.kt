package pl.perski.lukasz.marathon.ui.act.main

import android.content.Context
import pl.perski.lukasz.marathon.data.repositories.DBRepository

class MainModel : MainActivityMVP.Model {


    lateinit var repository : DBRepository


    override fun copyDB(context : Context) {
        repository = DBRepository(context)
        repository.copyDataBase()
    }

}