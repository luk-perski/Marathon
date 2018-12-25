package pl.perski.lukasz.maraton.ui.fragments.voiceRecorder


import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_timer.view.*
import kotlinx.android.synthetic.main.fragment_voice_recorder.view.*

import pl.perski.lukasz.maraton.R

class VoiceRecFragment : Fragment(), VoiceRecFragmentMVP.View {


    override fun changeBtnIcon(state: Boolean) {
        if (!state) {
            currentView.btnRecord.setImageResource(R.drawable.ic_mic_white_24dp)
        }
        else {
            currentView.btnRecord.setImageResource(R.drawable.ic_stop_white_24dp)
        }
    }

    override fun changeChronometerState(state: Boolean) {

        if (state) {
            currentView.chronometerVoiceRec.base = SystemClock.elapsedRealtime()
            currentView.chronometerVoiceRec.start()
        }
        else {
            currentView.chronometerVoiceRec.stop()
            currentView.chronometerVoiceRec.base = SystemClock.elapsedRealtime()
        }
        }



    var presenter = VoiceRecFragmentPresenter()
    private var mStartRecording = false






    override fun getContext(): Context {
        return activity!!.applicationContext
    }

    override fun setToolbarTittle(title: String, subtitle: String) {
        (activity as AppCompatActivity).supportActionBar!!.subtitle = subtitle
        (activity as AppCompatActivity).supportActionBar!!.title = title
    }

    override fun setEvents() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


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


        presenter.setView(this)




        currentView.btnRecord.setOnClickListener {
            mStartRecording = !mStartRecording
            presenter.onRecord(mStartRecording)

        }
        return currentView
    }

}


