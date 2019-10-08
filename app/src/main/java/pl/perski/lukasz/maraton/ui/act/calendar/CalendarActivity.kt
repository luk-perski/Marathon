package pl.perski.lukasz.maraton.ui.act.calendar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_calendar.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.adapters.ExerciseDoneListAdapter
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData
import spencerstudios.com.fab_toast.FabToast

class CalendarActivity : AppCompatActivity(), CalendarActivityMVP.View {

    var presenter = CalendarActivityPresenter()
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        presenter.setView(this)
        setEvents()
        setControls()
    }

    fun setControls() {
        setSupportActionBar(toolbarCalendar)

        // Now get the support action bar
        val actionBar = supportActionBar

        // Set toolbar title/app
        actionBar!!.title = resources.getString(R.string.calendar)

        // Set action bar/toolbar sub title
        //actionBar.subtitle = resources.getString(R.string.tap_menu_to_generate_card)

        // Set action bar elevation
        actionBar.elevation = 4.0F

        // Display the app icon in action bar/toolbar
        actionBar.setDisplayShowHomeEnabled(true)
    }


    override fun getContext(): Context {
        return this
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.calendar_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.generate_pdf -> {
                presenter.generateExerciseSheet(calendarView.currentDate)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setList(exerciseDoneList: ArrayList<ExerciseDoneData>) {
        val adapter = ExerciseDoneListAdapter(this, exerciseDoneList)
        lvCalendar?.adapter = adapter
        //TODO: clear listy
        adapter.notifyDataSetChanged()
    }

    override fun changeLvExercisesState(state: Boolean) {
        if (state) {
            lvCalendar.visibility = View.VISIBLE
        } else {
            lvCalendar.visibility = View.INVISIBLE
        }
    }

    override fun manageProgressBar(value: Int) {
        progressbarCal.visibility = value
    }

    override fun changeTvTapInfo(state: Boolean) {
        if (state) {
            tvTapInfo.visibility = View.VISIBLE
        } else {
            tvTapInfo.visibility = View.INVISIBLE
        }
    }

    fun setEvents() {

        this.calendarView.setOnDateChangedListener { widget, date, selected ->
            val normalizedMonth = date.month + 1
            presenter.getExercises("$normalizedMonth-${date.year}", "${date.day}-$normalizedMonth-${date.year}")

        }
    }
}


