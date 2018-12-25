package pl.perski.lukasz.maraton.ui.fragments.voiceRecorder

import android.content.Context
import pl.perski.lukasz.maraton.ui.fragments.timer.TimerFragmentMVP

interface VoiceRecFragmentMVP {

    interface View {
        fun getContext(): Context
        fun setToolbarTittle(title: String, subtitle: String)
        fun setEvents()
        fun changeChronometerState(state: Boolean)
        fun changeBtnIcon(state : Boolean)
    }

    interface Presenter {
        fun setControls()
        fun setView(view: View)
        fun onRecord(start: Boolean)
        fun  startRecording()
        fun setFileNameAndPath()
        fun stopRecording()
    }

    interface Model{}

}