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
import pl.perski.lukasz.maraton.ui.act.exercisesList.ExercisesListActivity
import android.view.animation.AlphaAnimation
import pl.perski.lukasz.maraton.adapters.SharedPrefHelper
import pl.perski.lukasz.maraton.ui.act.calendar.CalendarActivity
import pl.perski.lukasz.maraton.ui.act.intro.IntroActivity
import pl.perski.lukasz.maraton.ui.act.fragmentContainer.FragmentContainerActivity
import pl.perski.lukasz.maraton.ui.act.login.LoginActivity
import pl.perski.lukasz.maraton.ui.reminder.ReminderActivity
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.FRAGMENT
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.RECORDS
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.STOPWATCH
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.TIMER

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActivityMVP.View {

    var presenter = MainActivityPresenter()
    private val buttonClick = AlphaAnimation(1f, 0.8f)
    lateinit var sharedPrefHelper : SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPrefHelper = SharedPrefHelper(this)
        presenter.setView(this)
        if (sharedPrefHelper.firstLaunch) {
            finish()
            startActivity(Intent(this, IntroActivity::class.java))
        } else if (!presenter.checkAuth()){
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        setControls()
        setEvents()
    }

    fun setControls() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    fun setEvents() {

        //ĆWICZENIA PORANNE
        btnMorningTraining.setOnClickListener {
            btnMorningTraining.startAnimation(buttonClick)
            presenter.morningTraining()
        }

        //ĆWICZENIA WIECZORNE
        btnEveningTraining.setOnClickListener {
            btnEveningTraining.startAnimation(buttonClick)
            presenter.eveningTraining()
        }
    }

    override fun getContext(): Context {
        return this
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        menu.findItem(R.id.miLogout).isVisible = presenter.checkAuth()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.miLogout -> {
                presenter.logoutUser()
                true
            }
            R.id.miChooseMorning -> {
                presenter.chooser(3)
                true
            }
            R.id.miChooseEvening -> {
                presenter.chooser(4)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_exercises -> {
                val intent = Intent(applicationContext, ExercisesListActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_stopwatch -> {
                val intent = Intent(applicationContext, FragmentContainerActivity::class.java)
                intent.putExtra(FRAGMENT, STOPWATCH)
                startActivity(intent)
            }

            R.id.nav_timer -> {
                val intent = Intent(applicationContext, FragmentContainerActivity::class.java)
                intent.putExtra(FRAGMENT, TIMER)
                startActivity(intent)
            }
            R.id.nav_records -> {
                val intent = Intent(applicationContext, FragmentContainerActivity::class.java)
                intent.putExtra(FRAGMENT, RECORDS)
                startActivity(intent)
            }
            R.id.nav_calendar -> {
                val intent = Intent(applicationContext, CalendarActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_reminder -> {
                val intent = Intent(applicationContext, ReminderActivity::class.java)
                startActivity(intent)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun reloadActivity() {
        finish()
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }


    override fun startIntroActivity() {
        val intent = Intent(this, IntroActivity::class.java)
        startActivity(intent)
    }

    override fun finishAct() {
        finish()
    }
}
