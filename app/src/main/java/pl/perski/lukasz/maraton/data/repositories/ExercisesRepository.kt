package pl.perski.lukasz.maraton.data.repositories

import android.content.Context
import pl.perski.lukasz.maraton.data.db.DbWorkerThread
import pl.perski.lukasz.maraton.data.db.ExercisesDB
import pl.perski.lukasz.maraton.data.model.ExerciseData
//TODO: Poczytaj o wzorcu Repository
class ExercisesRepository (var context : Context){

    private var mDb : ExercisesDB? = null
    private lateinit var mDbWorkerThread: DbWorkerThread

    fun getExercises() : List<ExerciseData>
    {
        prepareDbWorker()
        return mDb?.exerciseDataDao()?.getAll()!!
    }

    fun getExercisesGroupId() : MutableList<Int>
    {
        prepareDbWorker()
        return mDb?.exerciseDataDao()?.getGroupType()!!
    }

    fun getExercisesGroup(groupId : Int) : MutableList<ExerciseData>
    {
        prepareDbWorker()
        return mDb?.exerciseDataDao()?.getGroup(groupId)!!
    }

    fun getExerciseTitlesGroup(groupId : Int) : MutableList<String>
    {
        prepareDbWorker()
        return mDb?.exerciseDataDao()?.getTitlesGroup(groupId)!!
    }

    fun prepareDbWorker()
    {
        mDb = ExercisesDB.getInstance(context)
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
    }
}