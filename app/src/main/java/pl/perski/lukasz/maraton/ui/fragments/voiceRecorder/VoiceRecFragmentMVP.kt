package pl.perski.lukasz.maraton.ui.fragments.voiceRecorder

import android.content.Context

interface VoiceRecFragmentMVP {

    interface View {
        fun getContext(): Context
        fun setToolbarTittle(title: String, subtitle: String)
        fun changeChronometerState(state: Boolean)
        fun changeBtnIcon(state: Boolean)
        fun changeBtnVisibility()
    }

    interface Presenter {
        fun setControls()
        fun setView(view: View)
        fun onRecord(start: Boolean)
        fun startRecording()
        fun setFileNameAndPath()
        fun stopRecording()
        fun getPatchSet(): String
    }

    interface Model

}