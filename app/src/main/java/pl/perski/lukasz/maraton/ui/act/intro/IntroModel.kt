package pl.perski.lukasz.maraton.ui.act.intro

import android.content.Context
import pl.perski.lukasz.maraton.data.repositories.DBRepository

class IntroModel : IntroActivityMVP.Model {

    lateinit var repository: DBRepository

    override fun copyDB(context: Context) {
        repository = DBRepository(context)
        repository.copyDataBase()
    }
}