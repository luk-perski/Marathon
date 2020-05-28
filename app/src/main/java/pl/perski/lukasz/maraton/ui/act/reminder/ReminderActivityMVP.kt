package pl.perski.lukasz.maraton.ui.act.reminder

import android.content.Context

interface ReminderActivityMVP {

    interface View {
        fun getContext(): Context
        fun getHour(): Int
        fun getMinute(): Int
        fun setReminderInfo(string: String?)
    }

    interface Presenter {
        fun setView(view: View)
        fun setReminder()
        fun cancelReminder()
        fun getReminderInfo(): String?
        fun setReminderInfo(date: String)

    }

    interface Model {
        fun getReminderInfo(contex: Context): String?
        fun setReminderInfo(date: String, contex: Context)
    }
}