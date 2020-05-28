package pl.perski.lukasz.maraton.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "twoVowelsWords")
data class TwoVowelsWordsData(

        @PrimaryKey
        @SerializedName("recId")
        val recId: Int,
        @ColumnInfo(name = "body")
        @SerializedName("body")
        val body: String) {
    override fun toString(): String {
        return body
    }
}