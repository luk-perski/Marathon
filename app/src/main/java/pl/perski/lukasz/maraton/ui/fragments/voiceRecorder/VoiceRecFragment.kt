package pl.perski.lukasz.maraton.ui.fragments.voiceRecorder


import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_voice_recorder.view.*

import pl.perski.lukasz.maraton.R

class VoiceRecFragment : Fragment(), VoiceRecFragmentMVP.View {


    override fun changeBtnIcon(state: Boolean) {
        if (!state) {
            currentView.btnRecord.setImageResource(R.drawable.ic_mic_white_24dp)
        } else {
            currentView.btnRecord.setImageResource(R.drawable.ic_stop_white_24dp)
        }
    }

    override fun changeChronometerState(state: Boolean) {

        if (state) {
            currentView.chronometerVoiceRec.base = SystemClock.elapsedRealtime()
            currentView.chronometerVoiceRec.start()
        } else {
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
        TODO("not implemented") //To change titles of created functions use File | Settings | File Templates.
    }


    lateinit var currentView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        currentView = inflater.inflate(R.layout.fragment_voice_recorder, container, false)
        presenter.setView(this)
        currentView.btnRecord.setOnClickListener {
            mStartRecording = !mStartRecording
            presenter.onRecord(mStartRecording)

        }
        return currentView
    }
}


