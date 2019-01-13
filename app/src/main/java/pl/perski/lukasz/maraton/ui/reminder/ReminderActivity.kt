package pl.perski.lukasz.maraton.ui.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_reminder.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.services.ReminderReceiver
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.ALARM_STATE
import spencerstudios.com.fab_toast.FabToast
import java.util.*
//TODO: MVP!!!
class ReminderActivity : AppCompatActivity() {

    private val buttonClick = AlphaAnimation(1f, 0.8f)
    lateinit var alarmManager: AlarmManager
    lateinit var calendar: Calendar
    lateinit var pi: PendingIntent
    private var hour = 0
    private var min = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        calendar = Calendar.getInstance()

        tpReminder.setIs24HourView(true)
        tpReminder.currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        tpReminder.currentMinute = calendar.get(Calendar.MINUTE)

        val intent = Intent(applicationContext, ReminderReceiver::class.java)


        btnSetReminder.setOnClickListener {
            btnSetReminder.startAnimation(buttonClick)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hour = tpReminder.hour
                min = tpReminder.minute
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, min)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
            } else {
                hour = tpReminder.currentHour
                min = tpReminder.currentMinute
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, min)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
            }
            intent.putExtra(ALARM_STATE, true)
            pi = PendingIntent.getBroadcast(this@ReminderActivity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pi)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 24*60*60*1000, pi)
            FabToast.makeText(this, resources.getString(R.string.reminder_set), FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show()
        }

        btnDeleteReminder.setOnClickListener {
            btnDeleteReminder.startAnimation(buttonClick)
            intent.putExtra(ALARM_STATE, false)
            pi = PendingIntent.getBroadcast(this@ReminderActivity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pi)
            alarmManager.cancel(pi)
            sendBroadcast(intent)
        }
    }
}
