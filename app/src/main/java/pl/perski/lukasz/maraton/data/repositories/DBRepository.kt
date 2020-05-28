package pl.perski.lukasz.maraton.data.repositories

import android.content.Context
import pl.perski.lukasz.maraton.data.db.DatabaseHelper

class DBRepository(var context: Context) {
    fun copyDataBase() {
        DatabaseHelper.copyDataBase(context)
    }
}