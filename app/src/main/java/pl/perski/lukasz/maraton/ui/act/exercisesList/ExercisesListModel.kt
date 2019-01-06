package pl.perski.lukasz.maraton.ui.act.exercisesList

import android.content.Context
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.repositories.ExercisesRepository

class ExercisesListModel : ExercisesListActivityMVP.Model {

    lateinit var repository: ExercisesRepository

    override fun getExerciseTitlesGroupFromDB(context: Context, groupId: Int): MutableList<String>? {
        repository = ExercisesRepository(context)
        return repository.getExerciseTitlesGroup(groupId)
    }

    override fun getExercisesGroupFromDB(context: Context, groupId: Int): MutableList<ExerciseData>? {
        repository = ExercisesRepository(context)
        return repository.getExercisesGroup(groupId)
    }

    override fun getExercisesGroupIdFromDB(context: Context): MutableList<Int>? {
        repository = ExercisesRepository(context)
        return repository.getExercisesGroupId()
    }


    override fun getExercisesFromDB(context: Context): List<ExerciseData> {
        repository = ExercisesRepository(context)
        return repository.getExercises()
    }
}