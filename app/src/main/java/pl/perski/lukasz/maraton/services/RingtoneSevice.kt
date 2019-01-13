package pl.perski.lukasz.maraton.services

import android.app.Service
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.IBinder

class RingtoneSevice : Service() {

    companion object {
        lateinit var ring : Ringtone
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        playSound()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun playSound(){
        val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        RingtoneManager.getRingtone(baseContext, alarmUri).play()
    }

}