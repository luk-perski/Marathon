package pl.perski.lukasz.maraton.ui.act.fragmentContainer

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_fragment_container.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.utils.CONST_STRINGS
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.EXERCISE
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.FRAGMENT
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.SHOW_BACK_BTN

class FragmentContainerActivity : AppCompatActivity(), FragmentContainerActMVP.View {

    var presenter = FragmentContainerActPresenter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        presenter.setView(this)
        presenter.showFragment(intent)
        setControls()
        setEvents()
    }

    private fun setControls() {
        if (intent.getBooleanExtra(SHOW_BACK_BTN, false)) {
            btnBack.visibility = View.VISIBLE
        }
    }

    private fun setEvents() {
        btnEndExercise.setOnClickListener {
            finish()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun getContext(): Context {
        return this
    }

    override fun setBtnEndExercise(state: Boolean) {
        if (state) {
            btnEndExercise.visibility = View.VISIBLE
        } else {
            btnEndExercise.visibility = View.INVISIBLE
        }
    }
}
