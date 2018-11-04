package pl.perski.lukasz.marathon.data.enums

import java.util.*

enum class AlarmType private constructor(val code: Int, val label: String) {
    TYPE_1(1, "fragment_exercise_type_one"),
    TYPE_2(2, "Przesunięty alarm początku pracy");


    companion object {

        fun getByCode(code: Int): String {
            val alarmTypes = ArrayList(Arrays.asList(*values()))
            return alarmTypes[code - 1].label
        }
    }
}