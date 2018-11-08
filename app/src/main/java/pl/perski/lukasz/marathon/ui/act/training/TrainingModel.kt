package pl.perski.lukasz.marathon.ui.act.training

import android.content.Context
import pl.perski.lukasz.marathon.data.model.ExerciseData
import pl.perski.lukasz.marathon.data.repositories.ExercisesRepository

class TrainingModel : TrainingActivityMVP.Model {

    lateinit var repository : ExercisesRepository

    override fun getExercisesFromDB(context: Context): List<ExerciseData> {
        repository = ExercisesRepository(context)
        return repository.getExercises()
    }
}