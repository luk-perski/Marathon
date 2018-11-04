package pl.perski.lukasz.marathon.data.repositories

import android.content.Context
import pl.perski.lukasz.marathon.data.db.DbWorkerThread
import pl.perski.lukasz.marathon.data.db.ExercisesDB
import pl.perski.lukasz.marathon.data.model.ExerciseData

class ExercisesRepository (var context : Context){

    private lateinit var exercisesList : List<ExerciseData>
    private var mDb : ExercisesDB? = null
    private lateinit var mDbWorkerThread: DbWorkerThread

    fun getExercises() : List<ExerciseData>
    {
        mDb = ExercisesDB.getInstance(context)
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        exercisesList = mDb?.exerciseDataDao()?.getAll()!!
        return exercisesList

    }


}