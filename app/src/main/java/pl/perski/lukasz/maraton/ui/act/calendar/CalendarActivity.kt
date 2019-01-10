package pl.perski.lukasz.maraton.ui.act.calendar

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_calendar.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.R.id.calendarView
import pl.perski.lukasz.maraton.adapters.ExerciseDoneListAdapter
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData
import pl.perski.lukasz.maraton.ui.fragments.calendar.CalendarFragmentPresenter

class CalendarActivity : AppCompatActivity(), CalendarActivityMVP.View {

    var presenter = CalendarActivityPresenter()
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        presenter.setView(this)
        setEvents()
    }

    override fun getContext(): Context {
        return this
    }

    override fun setList(exerciseDoneList: ArrayList<ExerciseDoneData>) {

        var  adapter = ExerciseDoneListAdapter(this, exerciseDoneList)

        lvCalendar?.adapter = adapter
        adapter?.notifyDataSetChanged()
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
        this.calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var normalizedMonth = month + 1
            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
            Log.e("siemano", msg)
            presenter.getExercises("$normalizedMonth-$year","$dayOfMonth-$normalizedMonth-$year")
        }
    }

}
