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

class ExerciseDoneListAdapter(private var activity: Activity, private var items: ArrayList<ExerciseDoneData): BaseAdapter() {
    >
    private class ViewHolder(row: View?) {
        var tvTitle: TextView? = null
        var tvAmountQue : TextView? = null
        var tvAmountAns: TextView? = null
        var tvMaxQue : TextView? = null
        var tvMaxAns : TextView? = null
        var tvSkipped : TextView? = null
        var ivDone: ImageView? = null

        init {
            this.tvTitle = row?.findViewById(R.id.tvTitleItem)
            this.tvAmountQue = row?.findViewById(R.id.tvAmountQue)
            this.tvAmountAns = row?.findViewById(R.id.tvAmountAns)
            this.tvMaxQue = row?.findViewById(R.id.tvMaxQue)
            this.tvMaxAns = row?.findViewById(R.id.tvMaxAns)
            this.tvSkipped = row?.findViewById(R.id.tvExerciseWasSkipped)
            this.ivDone = row?.findViewById(R.id.ivDone)
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

        val exerciseDoneData = items[position]

        viewHolder.tvTitle?.text = exerciseDoneData.title

        if (exerciseDoneData.done) {
            viewHolder.tvSkipped!!.visibility = View.INVISIBLE
             viewHolder.ivDone!!.setBackgroundResource(( R.drawable.ic_exercise_done_green_64p))

            if (exerciseDoneData.repeatAmount != null)
            {
                viewHolder.tvAmountQue!!.visibility = View.VISIBLE
                viewHolder.tvAmountAns!!.visibility = View.VISIBLE
                viewHolder.tvAmountQue!!.text = view!!.resources.getString(R.string.amount_question)
                viewHolder.tvAmountAns?.text = exerciseDoneData.repeatAmount.toString()
            }
            else if (exerciseDoneData.timeAmount != null){
                viewHolder.tvAmountQue!!.text = view!!.resources.getString(R.string.time_question)
                viewHolder.tvAmountAns?.text = exerciseDoneData.timeAmount.toString()
            }

            if (exerciseDoneData.maxAmount != null)
            {
                viewHolder.tvMaxQue!!.visibility = View.VISIBLE
                viewHolder.tvMaxAns!!.visibility = View.VISIBLE
                viewHolder.tvMaxAns!!.text = exerciseDoneData.maxAmount.toString()
            }

            else{
                viewHolder.tvMaxQue!!.visibility = View.INVISIBLE
                viewHolder.tvMaxAns!!.visibility = View.INVISIBLE
            }
        }

        else{
            viewHolder.ivDone!!.setBackgroundResource(( R.drawable.ic_exercise_no_done_red_64dp))
            viewHolder.tvAmountQue!!.visibility = View.INVISIBLE
            viewHolder.tvAmountAns!!.visibility = View.INVISIBLE
            viewHolder.tvMaxQue!!.visibility = View.INVISIBLE
            viewHolder.tvMaxAns!!.visibility = View.INVISIBLE
            viewHolder.tvSkipped!!.visibility = View.VISIBLE
        }
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