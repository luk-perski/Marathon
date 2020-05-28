package pl.perski.lukasz.maraton.ui.act.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.services.ReminderReceiver
import pl.perski.lukasz.maraton.utils.ConstStrings
import spencerstudios.com.fab_toast.FabToast
import java.util.*


class ReminderActivityPresenter : ReminderActivityMVP.Presenter {
    override fun getReminderInfo(): String? {
        return model.getReminderInfo(context)
    }

    override fun setReminderInfo(date: String) {
        model.setReminderInfo(date, context)
    }


    private lateinit var view: ReminderActivityMVP.View
    private lateinit var context: Context
    lateinit var alarmManager: AlarmManager
    lateinit var calendar: Calendar
    lateinit var pi: PendingIntent
    lateinit var intent: Intent
    val model = ReminderModel()

    override fun setView(view: ReminderActivityMVP.View) {
        this.view = view
        context = view.getContext()
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        calendar = Calendar.getInstance()
        intent = Intent(context.applicationContext, ReminderReceiver::class.java)
    }

    override fun setReminder() {
        val min = view.getMinute()
        val hour = view.getHour()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, min)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        intent.putExtra(ConstStrings.ALARM_STATE, true)
        pi = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pi)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
                24 * 60 * 60 * 1000, pi)
        FabToast.makeText(context, context.resources.getString(R.string.reminder_set),
                FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_TOP).show()
        val date = "$hour : $min"
        model.setReminderInfo(date, context)
        view.setReminderInfo(date)
    }

    override fun cancelReminder() {
        intent.putExtra(ConstStrings.ALARM_STATE, false)
        pi = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pi)
        alarmManager.cancel(pi)
        context.sendBroadcast(intent)
        view.setReminderInfo(null)
        FabToast.makeText(context, context.resources.getString(R.string.reminder_cancel),
                FabToast.LENGTH_LONG, FabToast.INFORMATION, FabToast.POSITION_TOP).show()
    }
}