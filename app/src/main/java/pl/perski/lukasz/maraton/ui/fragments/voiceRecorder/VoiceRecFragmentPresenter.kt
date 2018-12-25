package pl.perski.lukasz.maraton.ui.fragments.voiceRecorder

import android.media.MediaRecorder
import android.os.Environment
import android.widget.Toast
import pl.perski.lukasz.maraton.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

//TODO: zrób zapis info o nagraniu do bazy danych

class VoiceRecFragmentPresenter : VoiceRecFragmentMVP.Presenter {

    private lateinit var mFileName: String
    private lateinit var mFilePath: String
    private lateinit var mRecorder: MediaRecorder
    private var mStartingTimeMillis: Long = 0
    private var mElapsedMillis: Long = 0
    val cal = Calendar.getInstance()!!
    val time = cal.time!!
    var date = SimpleDateFormat("yyyy_MM_dd_mm_ss", Locale.GERMANY).format(time)!!
    private lateinit var view: VoiceRecFragmentMVP.View

    override fun setView(view: VoiceRecFragmentMVP.View) {
        this.view = view
    }

    override fun setFileNameAndPath() {         //TODO: Zrób generacje tytuły w zależności od ćwiczenia
   mFileName = view.getContext().resources.getString(R.string.record) + "_$date"
        mFilePath = Environment.getExternalStorageDirectory().absolutePath + "/" +
               view.getContext().resources.getString(R.string.app_name) + "/" +
                view.getContext().resources.getString(R.string.records)
        val f = File(mFilePath)
        if (!f.exists()){
            f.mkdirs()
        }
    }

    override fun startRecording() { //TODO: Czy tutaj czegoś nie powienien robić Model?
        setFileNameAndPath()

        mRecorder = MediaRecorder()
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mRecorder.setOutputFile("$mFilePath/$mFileName.mp3")
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mRecorder.setAudioChannels(1)
        mRecorder.setAudioSamplingRate(44100)
        mRecorder.setAudioEncodingBitRate(192000)
        mRecorder.prepare()
        mRecorder.start()
        mStartingTimeMillis = System.currentTimeMillis()
    }

  override fun stopRecording()
    {
        mRecorder.stop()
        mElapsedMillis = System.currentTimeMillis() - mStartingTimeMillis
        mRecorder.release()
        Toast.makeText(view.getContext(), view.getContext().resources.getString(R.string.toast_recording_finish)+ mFilePath, Toast.LENGTH_LONG).show()
    }

    override fun onRecord(start: Boolean) {
        if (start) {
            view.changeBtnIcon(start)
            view.changeChronometerState(start)
            startRecording()
        } else {
            view.changeBtnIcon(start)
            view.changeChronometerState(start)
            stopRecording()
        }
    }

    override fun setControls() {
        //TODO: zmień na R.string
        view.setToolbarTittle("Nagrania", "")
    }
}