package pl.perski.lukasz.marathon.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.beust.klaxon.Json
import com.google.gson.annotations.SerializedName

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
        val picName: String?)
{
    override fun toString(): String {
        return "$title"
    }
}




