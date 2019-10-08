package pl.perski.lukasz.maraton.ui.act.reminder

import android.content.Context
import pl.perski.lukasz.maraton.utils.SharedPrefHelper

class ReminderModel : ReminderActivityMVP.Model {

    override fun getReminderInfo(context: Context): String? {
        val sharedPrefHelper = SharedPrefHelper(context)
        return sharedPrefHelper.reminderDate
    }

    override fun setReminderInfo(date: String, context: Context) {
        val sharedPrefHelper = SharedPrefHelper(context)
        sharedPrefHelper.reminderDate = date
    }
}