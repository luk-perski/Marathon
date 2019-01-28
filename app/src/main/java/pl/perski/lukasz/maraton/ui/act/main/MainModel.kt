package pl.perski.lukasz.maraton.ui.act.main

import pl.perski.lukasz.maraton.utils.SharedPrefHelper
import java.util.ArrayList

class MainModel : MainActivityMVP.Model {

    override fun saveChoice(titles: ArrayList<String>, mode: Int, sharedPrefHelper: SharedPrefHelper) {
        when (mode) {
            1 -> {
                sharedPrefHelper.morningExercises = titles.toMutableSet()
                sharedPrefHelper.firstMorning = false
            }
            2 -> {
                sharedPrefHelper.eveningExercises = titles.toMutableSet()
                sharedPrefHelper.firstEvening = false
            }
            3 -> {
                sharedPrefHelper.morningExercises = titles.toMutableSet()
            }
            4 -> {
                sharedPrefHelper.eveningExercises = titles.toMutableSet()
            }
        }
    }
}