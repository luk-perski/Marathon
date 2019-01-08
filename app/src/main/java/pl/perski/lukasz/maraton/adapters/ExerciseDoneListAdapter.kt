package pl.perski.lukasz.maraton.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData

class ExerciseDoneListAdapter(private var activity: Activity, private var items: ArrayList<ExerciseDoneData>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var tvTitle: TextView? = null
        var ivDone: ImageView? = null

        init {
            this.tvTitle = row?.findViewById<TextView>(R.id.tvTitleItem)
            this.ivDone = row?.findViewById<ImageView>(R.id.ivDone)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.exercise_done_list_item, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var exerciseDoneData = items[position]
        viewHolder.tvTitle?.text = exerciseDoneData.title

        return view as View
    }

    override fun getItem(i: Int): ExerciseDoneData {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}