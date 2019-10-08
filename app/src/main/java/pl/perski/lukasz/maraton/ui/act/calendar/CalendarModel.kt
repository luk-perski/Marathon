package pl.perski.lukasz.maraton.ui.act.calendar

import android.content.Context
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.repositories.ExercisesRepository

class CalendarModel: CalendarActivityMVP.Model {

    lateinit var repository: ExercisesRepository

    override fun getExercisesFromDB(context: Context): List<ExerciseData> {
        repository = ExercisesRepository(context)
        return repository.getExercises()
    }
}


