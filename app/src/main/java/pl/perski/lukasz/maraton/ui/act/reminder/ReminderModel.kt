package pl.perski.lukasz.maraton.ui.act.reminder

import android.content.Context
import pl.perski.lukasz.maraton.utils.SharedPrefHelper

class ReminderModel : ReminderActivityMVP.Model {

    override fun getReminderInfo(contex: Context): String? {
        val sharedPrefHelper = SharedPrefHelper(contex)
        return sharedPrefHelper.reminderDate
    }

    override fun setReminderInfo(date: String, contex: Context) {
        val sharedPrefHelper = SharedPrefHelper(contex)
        sharedPrefHelper.reminderDate = date
    }
}