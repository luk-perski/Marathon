package pl.perski.lukasz.maraton.ui.fragments.calendar


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.format.Time
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_exercises_list.*
import kotlinx.android.synthetic.main.fragment_calendar.view.*

import pl.perski.lukasz.maraton.R
import java.lang.Long.parseLong
import java.util.*

//TODO: zrobić ustawienie max i min date

class CalendarFragment : Fragment(), CalendarFragmentMVP.View {

    private val auth = FirebaseAuth.getInstance()
    lateinit var colRefExercise: CollectionReference
    lateinit var currentView: View
    var presenter = CalendarFragmentPresenter()
    lateinit var myListOfDocuments: List<DocumentSnapshot>
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getContext(): Context {
        return activity!!.applicationContext
    }

    override fun setToolbarTittle(title: String, subtitle: String) {
        (activity as AppCompatActivity).supportActionBar!!.subtitle = subtitle
        (activity as AppCompatActivity).supportActionBar!!.title = title
    }

    override fun setEvents() {
        currentView.calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
      Log.e("siemano", msg)
        }

    }

    @Throws(NumberFormatException::class)
    fun valueOf(s: String): Long {
        return java.lang.Long.valueOf(parseLong(s, 10))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        currentView = inflater.inflate(R.layout.fragment_calendar, container, false)
        presenter.setView(this)
        presenter.setControls()
        setEvents()
        return currentView
    }
}
