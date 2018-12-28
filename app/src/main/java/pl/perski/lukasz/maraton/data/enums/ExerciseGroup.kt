package pl.perski.lukasz.maraton.data.enums

import android.util.Log
import java.util.*

enum class ExerciseGroup constructor(val code: Int, val group: String) {
    //TODO: Zmień na R.id.string
    TYPE_1(1, "Oczy"),
    TYPE_2(2, "Usta i twarz"),
    TYPE_3(3, "Naprężające"),
    TYPE_4(4, "Głośne"),
    TYPE_5(5, "Wyciskanie przepony"),
    TYPE_6(6, "Wierszyki"),
    TYPE_7(7, "Spółgłoski"),
    TYPE_8(8, "Zdania&Wyrazy"),
    TYPE_9(9, "Połączenia głosek"),
    TYPE_10(10, "Masaże"),
    TYPE_11(11, "Czytanie&Inne");


    companion object {
        fun getByCode(code: Int): String {
            val exerciseGroup = ArrayList(Arrays.asList(*values()))
            return exerciseGroup[code-1].group
        }
    }
}