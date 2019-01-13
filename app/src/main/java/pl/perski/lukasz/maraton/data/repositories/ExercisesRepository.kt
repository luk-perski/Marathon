package pl.perski.lukasz.maraton.data.repositories

import android.content.Context
import pl.perski.lukasz.maraton.data.db.DbWorkerThread
import pl.perski.lukasz.maraton.data.db.MarathonDB
import pl.perski.lukasz.maraton.data.model.ExerciseData
//TODO: Poczytaj o wzorcu Repository
class ExercisesRepository (var context : Context) : BaseRepository() {


    fun getExercises(): List<ExerciseData> {
        prepareDbWorker(context)
        return mDb?.marathonDataDao()?.getAll()!!
    }

    fun getExerciseByTitle(title : String): ExerciseData? {
        prepareDbWorker(context)
        return mDb?.marathonDataDao()?.getExerciseByTitle(title)
    }

    fun getExercisesGroupId(): MutableList<Int> {
        prepareDbWorker(context)
        return mDb?.marathonDataDao()?.getGroupType()!!
    }

    fun getExercisesGroup(groupId: Int): MutableList<ExerciseData> {
        prepareDbWorker(context)
        return mDb?.marathonDataDao()?.getGroup(groupId)!!
    }

    fun getExerciseTitlesGroup(groupId: Int): MutableList<String> {
        prepareDbWorker(context)
        return mDb?.marathonDataDao()?.getTitlesGroup(groupId)!!
    }
}