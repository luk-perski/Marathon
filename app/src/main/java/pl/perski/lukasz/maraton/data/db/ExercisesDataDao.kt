package pl.perski.lukasz.maraton.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import pl.perski.lukasz.maraton.data.model.ExerciseData

@Dao
interface ExercisesDataDao {

    @Query(value = "SELECT * from exercises")
    fun getAll(): List<ExerciseData>

    @Query(value = "SELECT DISTINCT exerciseGroupId FROM exercises")
    fun getGroupType() : MutableList<Int>

    @Query(value = "SELECT *  FROM exercises WHERE exerciseGroupId = (:groupId)")
    fun getGroup(groupId : Int) : MutableList<ExerciseData>

    @Query(value = "SELECT title  FROM exercises WHERE exerciseGroupId = (:groupId)")
    fun getTitlesGroup(groupId : Int) : MutableList<String>

    @Insert(onConflict = REPLACE)
    fun insert(weatherData: ExerciseData)

    @Query("DELETE from exercises")
    fun deleteAll()
}