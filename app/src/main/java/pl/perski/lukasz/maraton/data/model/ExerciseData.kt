package pl.perski.lukasz.maraton.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "exercises")
data class ExerciseData(


       @PrimaryKey
        @SerializedName("recId")
        val recId: Int,

        @ColumnInfo(name = "title")
        @SerializedName("title")
        val title: String,

        @ColumnInfo(name = "exerciseGroupId")
        @SerializedName("exerciseGroupId")
        val exerciseGroupId : Int,

        @ColumnInfo(name = "exerciseTypeId")
        @SerializedName("exerciseTypeId")
        val exerciseTypeId: Int,

        @ColumnInfo(name = "extras")
        @SerializedName("extras")
        val extras: String?,

        @ColumnInfo(name = "picName")
        @SerializedName("picName")
        val picName: String?,

       @ColumnInfo(name = "isMorning")
       @SerializedName("isMorning")
       val isMorning: Boolean?,

       @ColumnInfo(name = "isEvening")
           @SerializedName("isEvening")
           val isEvening: Boolean?) : Serializable

{
    override fun toString(): String {
        return title
    }
}




