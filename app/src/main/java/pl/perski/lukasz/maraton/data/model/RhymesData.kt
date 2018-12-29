package pl.perski.lukasz.maraton.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "rhymes")
data class RhymesData(

        @PrimaryKey
        @SerializedName("name")
        val name: String,

        @ColumnInfo(name = "body")
        @SerializedName("body")
        val body: String,

        @ColumnInfo(name = "exerciseRecId")
        @SerializedName("exerciseRecId")
        val exerciseRecId : String) {

    override fun toString(): String {
        return body
    }
}


