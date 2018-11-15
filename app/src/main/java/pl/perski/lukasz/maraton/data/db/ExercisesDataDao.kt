package pl.perski.lukasz.maraton.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import pl.perski.lukasz.maraton.data.model.ExerciseData

@Dao
interface ExercisesDataDao {

    @Query("SELECT * from exercises")
    fun getAll(): List<ExerciseData>

    @Insert(onConflict = REPLACE)
    fun insert(weatherData: ExerciseData)

    @Query("DELETE from exercises")
    fun deleteAll()

}