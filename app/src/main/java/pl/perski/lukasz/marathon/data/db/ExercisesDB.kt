package pl.perski.lukasz.marathon.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import pl.perski.lukasz.marathon.data.model.ExerciseData

@Database(entities = arrayOf(ExerciseData::class), version = 1)
abstract class ExercisesDB : RoomDatabase() {

    abstract fun exerciseDataDao(): ExercisesDataDao

    companion object {
        private var INSTANCE: ExercisesDB? = null

        fun getInstance(context: Context): ExercisesDB? {
            if (INSTANCE == null) {
                synchronized(ExercisesDB::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
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