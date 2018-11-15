package pl.perski.lukasz.maraton.ui.act.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.ui.act.exercises.ExercisesActivity
import pl.perski.lukasz.maraton.ui.act.training.TrainingActivity
import android.view.animation.AlphaAnimation
import pl.perski.lukasz.maraton.ui.act.fragmentContainer.FragmentContainerActivity


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActivityMVP.View {


    var presenter = MainActivityPresenter()
    val STOPWATCH = "stopwatch"
    val TIMER = "timer"
    val RECORDS = "records"
    private val buttonClick = AlphaAnimation(1f, 0.8f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        presenter.setView(this)
//        presenter.onFirstLunch()
        presenter.grantPermissions()
        btnMorningTraining.setOnClickListener {
            btnMorningTraining.startAnimation(buttonClick)
            val intent = Intent(applicationContext, TrainingActivity::class.java)
            startActivity(intent) }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_slideshow -> {
                //TODO: kopia db i sprawdzenie uprawnienień podczas splash screenu
                presenter.copyDB()
                val intent = Intent(applicationContext, ExercisesActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_stopwatch -> {
                val intent = Intent(applicationContext, FragmentContainerActivity::class.java)
                intent.putExtra("fragment", STOPWATCH)
                startActivity(intent)
            }

            R.id.nav_timer -> {
                val intent = Intent(applicationContext, FragmentContainerActivity::class.java)
                intent.putExtra("fragment", TIMER)
                startActivity(intent)
            }
            R.id.nav_records -> {
                val intent = Intent(applicationContext, FragmentContainerActivity::class.java)
                intent.putExtra("fragment", RECORDS)
                startActivity(intent)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun getContext(): Context {
        return this
    }
}
