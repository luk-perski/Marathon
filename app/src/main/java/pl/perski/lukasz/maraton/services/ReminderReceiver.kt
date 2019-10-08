package pl.perski.lukasz.maraton.services

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.ALARM_STATE
import android.R.attr.name
import android.app.NotificationChannel
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.ui.act.main.MainActivity


class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent!!.getBooleanExtra(ALARM_STATE, false)) {
            val NOTIFICATION_ID = 234
            val CHANNEL_ID = "my_channel_01"
            val name = "my_channel"
            val Description = "This is my channel"

            val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_HIGH
                val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
                mChannel.description = Description
                mChannel.enableLights(true)
                mChannel.enableVibration(true)
                notificationManager.createNotificationChannel(mChannel)
            }

            val builder = NotificationCompat.Builder(context!!, CHANNEL_ID)
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