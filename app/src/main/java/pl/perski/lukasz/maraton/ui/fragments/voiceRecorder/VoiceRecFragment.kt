package pl.perski.lukasz.maraton.ui.fragments.voiceRecorder


import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import pl.perski.lukasz.maraton.R

class VoiceRecFragment : Fragment() {


    lateinit var currentView: View
    private val MY_PERMISSIONS_REQUEST = 101

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        currentView = inflater.inflate(R.layout.fragment_voice_recorder, container, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission
                    (currentView.context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(currentView.context as Activity,
                                Manifest.permission.RECORD_AUDIO)) {
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(currentView.context as Activity,
                            arrayOf(Manifest.permission.RECORD_AUDIO),
                            MY_PERMISSIONS_REQUEST)
                }
            }

        }
        return currentView
    }
}


