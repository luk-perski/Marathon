package pl.perski.lukasz.marathon.ui.act.training

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pl.perski.lukasz.marathon.R
import pl.perski.lukasz.marathon.ui.fragments.ExerciseFragmentTypeOne

class TrainingActivity : AppCompatActivity() {

    val manager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
        ShowFragmentOne()

    }

    fun ShowFragmentOne() {
        val transaction = manager.beginTransaction()
        val fragment = ExerciseFragmentTypeOne()
        transaction.replace(R.id.fragment_container, fragment  )
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {

        val count = manager.backStackEntryCount

        if (count == 1) {
            super.onBackPressed()
           this.finish()
        } else {
           manager.popBackStack()
        }

    }
}
