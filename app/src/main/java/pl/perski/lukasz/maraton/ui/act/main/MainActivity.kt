package pl.perski.lukasz.maraton.ui.act.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.ui.act.exercises.ExercisesActivity
import pl.perski.lukasz.maraton.ui.act.training.TrainingActivity
import android.view.animation.AlphaAnimation
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import pl.perski.lukasz.maraton.ui.act.fragmentContainer.FragmentContainerActivity
import pl.perski.lukasz.maraton.ui.act.login.LoginActivity


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActivityMVP.View {

    var presenter = MainActivityPresenter()
    val STOPWATCH = "stopwatch"
    val TIMER = "timer"
    val RECORDS = "records"
    val CALENDAR = "calendar"
    val FRAGMENT = "fragment"
    private val buttonClick = AlphaAnimation(1f, 0.8f)
    private  val auth = FirebaseAuth.getInstance()

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

    override fun onStart() {
        super.onStart()
        //TODO: Przenieś to do presentera
        if (auth.currentUser == null) {
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        else
            Log.e("uId", auth.uid)
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
        menu.findItem(R.id.action_logout).isVisible = auth.currentUser != null
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_logout -> {
                auth.signOut()
                Toast.makeText(this, "Nastąpiło wylogowanie.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
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
                val intent = Intent(applicationContext, FragmentContainerActivity::class.java)
                intent.putExtra(FRAGMENT, CALENDAR)
                startActivity(intent)
            }
            R.id.nav_login -> {
                val intent = Intent(applicationContext, LoginActivity::class.java)
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
