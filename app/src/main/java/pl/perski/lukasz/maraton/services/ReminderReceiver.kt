package pl.perski.lukasz.maraton.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.ui.act.main.MainActivity
import pl.perski.lukasz.maraton.utils.ConstStrings.Companion.ALARM_STATE


class ReminderReceiver : BroadcastReceiver() {
    private val NOTIFICATION_ID = 234
    private val CHANNEL_ID = "my_channel_01"
    private val NAME = "my_channel"
    private val DESCRIPTION = "This is my channel"

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent!!.getBooleanExtra(ALARM_STATE, false)) {


            val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_HIGH
                val mChannel = NotificationChannel(CHANNEL_ID, NAME, importance)
                mChannel.description = DESCRIPTION
                mChannel.enableLights(true)
                mChannel.enableVibration(true)
                notificationManager.createNotificationChannel(mChannel)
            }

            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher) //TODO: tutaj ikonka aplikacji
                    .setContentTitle(context.resources.getString(R.string.evening_exercises_reminder))
                    .setContentText(context.resources.getString(R.string.tap_to_start))
                    .setVibrate(longArrayOf(100, 200, 300, 400, 500, 100))
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setAutoCancel(true)
            val resultIntent = Intent(context, MainActivity::class.java)
            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(MainActivity::class.java)
            stackBuilder.addNextIntent(resultIntent)
            val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

            builder.setContentIntent(resultPendingIntent)
            notificationManager.notify(NOTIFICATION_ID, builder.build())
        }
    }
}