package pl.perski.lukasz.marathon.data.repositories

import android.content.Context
import pl.perski.lukasz.marathon.data.db.DatabaseHelper

class DBRepository(var context : Context) {
    fun copyDataBase(){
    DatabaseHelper.copyDataBase(context)
}}