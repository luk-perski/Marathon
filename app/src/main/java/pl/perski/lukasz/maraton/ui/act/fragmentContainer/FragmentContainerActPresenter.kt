package pl.perski.lukasz.maraton.ui.act.fragmentContainer

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.repositories.ExercisesRepository
import pl.perski.lukasz.maraton.ui.fragments.exerciseFragments.ExerciseBaseFragment
import pl.perski.lukasz.maraton.ui.fragments.stopwatch.StopwatchFragment
import pl.perski.lukasz.maraton.ui.fragments.timer.TimerFragment
import pl.perski.lukasz.maraton.ui.fragments.voiceRecorder.VoiceRecFragment
import pl.perski.lukasz.maraton.utils.CONST_STRINGS
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.EXERCISE
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.EXERCISE_TITLE
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.RECORDS
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.STOPWATCH
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.TIMER
import pl.perski.lukasz.maraton.utils.FragmentUtils

class FragmentContainerActPresenter(var manager : FragmentManager)  : FragmentContainerActMVP.Presenter{


    var fragment: Fragment? = null
    private lateinit var context: Context
    lateinit var repository: ExercisesRepository
    private lateinit var view : FragmentContainerActMVP.View
    private lateinit var exercise : ExerciseData

    override fun setView(view: FragmentContainerActMVP.View) {
        this.view = view
        context = view.getContext()
    }
    override fun showFragment(intent : Intent) {

        val exerciseTitle = intent.getStringExtra(EXERCISE_TITLE)
        if (!exerciseTitle.isNullOrEmpty())
        {
            view.setBtnEndExercise(true)
            repository = ExercisesRepository(context)
            exercise = repository.getExerciseByTitle(exerciseTitle)!!
        }
        when (intent.getStringExtra(CONST_STRINGS.FRAGMENT)){
            STOPWATCH -> fragment = StopwatchFragment()
            TIMER -> fragment = TimerFragment()
            RECORDS -> fragment = VoiceRecFragment()
            EXERCISE -> fragment =  ExerciseBaseFragment.newInstance(exercise)
        }
        FragmentUtils.replaceFragmentToActivity(manager, R.id.fragment_container, fragment!!)
    }
}