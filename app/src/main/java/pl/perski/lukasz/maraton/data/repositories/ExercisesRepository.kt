package pl.perski.lukasz.maraton.data.repositories

import android.content.Context
import pl.perski.lukasz.maraton.data.db.DbWorkerThread
import pl.perski.lukasz.maraton.data.db.ExercisesDB
import pl.perski.lukasz.maraton.data.model.ExerciseData
//TODO: Poczytaj o wzorcu Repository
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