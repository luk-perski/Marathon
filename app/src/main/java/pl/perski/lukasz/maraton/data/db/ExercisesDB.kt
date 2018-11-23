package pl.perski.lukasz.maraton.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import pl.perski.lukasz.maraton.data.model.ExerciseData

@Database(entities = arrayOf(ExerciseData::class), version = 1, exportSchema = false)
abstract class ExercisesDB : RoomDatabase() {

    abstract fun exerciseDataDao(): ExercisesDataDao

    companion object {
        private var INSTANCE: ExercisesDB? = null

        fun getInstance(context: Context): ExercisesDB? {
            if (INSTANCE == null) {
                synchronized(ExercisesDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ExercisesDB::class.java, "marathon.db").allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}