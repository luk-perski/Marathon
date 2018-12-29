package pl.perski.lukasz.maraton.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "twoVowelsWords")
data class TwoVowelsWordsData(

        @PrimaryKey
        @SerializedName("recId")
        val recId: Int,

        @ColumnInfo(name = "body")
        @SerializedName("body")
        val body: String)
{
    override fun toString(): String {
        return body
    }
}