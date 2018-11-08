package pl.perski.lukasz.marathon.ui.act.training

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_training.*
import pl.perski.lukasz.marathon.R

class TrainingActivity : AppCompatActivity(), TrainingActivityMVP.View
{

    var presenter = TrainingActivityPresenter(supportFragmentManager)
    private val buttonClick = AlphaAnimation(1f, 0.8f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
        presenter.setView(this)
        presenter.startTraining()
        //TODO: dodać interakcję z przyciskiem
        nextFragment.setOnClickListener {
            nextFragment.startAnimation(buttonClick)
            presenter.showFragment() }

    }

    override fun getContext(): Context {
        return this
    }

    override fun onBackPressed() {
//        odkomentowane, jeżeli ma istnieć możliwość do poprzedniego fragmentu
//        val count = manager.backStackEntryCount
//        if (count <2) {
           this.finish()
//        } else {
//           manager.popBackStack()
//        }
    }

    override fun setToolbarTittle(title: String, subtitle: String) {
        supportActionBar?.title = title
        supportActionBar?.subtitle = subtitle
    }

    override fun lockNextBtn() {
        nextFragment.isClickable = false
    }




}
