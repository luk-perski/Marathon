package pl.perski.lukasz.maraton.adapters

import android.content.Context
import android.preference.PreferenceManager

class SharedPrefHelper (context: Context) {

    companion object {

        const val FIRST_LAUNCH_KEY = "firstLaunch"
        const val FIRST_MORNING_KEY = "firstMorning"
        const val FIRST_EVENING_KEY = "firstEvening"
        const val MORNING_EXERCISES = "morning_exercises"
        const val EVENING_EXERCISES = "evening_exercises"
        const val CUSTOM_TRAINING_NAMES = "custom_exercises_titles"
        const val CUSTOM_EXERCISES_SET = "custom_exercises"
        const val REMINDER_DAILY = "rminder_daily"
        const val REMINDER_HOUR = "reminder_hour"
        const val REMINDER_MINUTE = "reminder_minute"

    }

    val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var morningExercises = preferences.getStringSet(MORNING_EXERCISES, null)
        set(value) = preferences.edit().putStringSet(MORNING_EXERCISES, value).apply()

    var customTrainingNames = preferences.getStringSet(CUSTOM_TRAINING_NAMES, null)
        set(value) = preferences.edit().putStringSet(CUSTOM_TRAINING_NAMES, value).apply()

    var customTrainingExerciesSet= preferences.getStringSet(CUSTOM_EXERCISES_SET, null)
        set(value) = preferences.edit().putStringSet(CUSTOM_EXERCISES_SET, value).apply()

    var eveningExercises = preferences.getStringSet(EVENING_EXERCISES, null)
        set(value) = preferences.edit().putStringSet(EVENING_EXERCISES, value).apply()

    var firstLaunch = preferences.getBoolean(FIRST_LAUNCH_KEY, true)
        set(value) = preferences.edit().putBoolean(FIRST_LAUNCH_KEY,value).apply()

    var firstMorning = preferences.getBoolean(FIRST_MORNING_KEY, true)
        set(value) = preferences.edit().putBoolean(FIRST_MORNING_KEY,value).apply()

     var firstEvening = preferences.getBoolean(FIRST_EVENING_KEY, true)
        set(value) = preferences.edit().putBoolean(FIRST_EVENING_KEY,value).apply()

    var reminderDaily = preferences.getBoolean(REMINDER_DAILY, false)
        set(value) = preferences.edit().putBoolean(REMINDER_DAILY,value).apply()

    var reminderHour = preferences.getInt(REMINDER_HOUR, -1)
        set(value) = preferences.edit().putInt(REMINDER_HOUR,value).apply()

    var reminderMinute = preferences.getInt(REMINDER_MINUTE, -1)
        set(value) = preferences.edit().putInt(REMINDER_MINUTE,value).apply()


    fun checkIfExists(name : String) : Boolean
    {
        return    preferences.contains(name)
    }


}