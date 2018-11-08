package pl.perski.lukasz.marathon.data.enums

import android.util.Log
import pl.perski.lukasz.marathon.R
import java.util.*

enum class ExerciseType private constructor(val code: Int, val id: Int) {
    TYPE_1(1, R.id.fragment_container),
    TYPE_2(2, R.id.fragment_container),
    TYPE_3(3, R.id.fragment_container),
    TYPE_4(4, R.id.fragment_container),
    TYPE_5(5, R.id.fragment_container),
    TYPE_6(6, R.id.fragment_container),
    TYPE_7(7, R.id.fragment_container),
    TYPE_8(8, R.id.fragment_container),
    TYPE_9(9, R.id.fragment_container);



    companion object {

        fun getByCode(code: Int): Int {

            val alarmTypes = ArrayList(Arrays.asList(*values()))
            Log.i("EXER", alarmTypes[code].id.toString())
            return alarmTypes[code].id
        }
    }
}