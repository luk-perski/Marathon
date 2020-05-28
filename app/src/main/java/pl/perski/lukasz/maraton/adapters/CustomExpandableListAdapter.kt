package pl.perski.lukasz.maraton.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.TextView
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.ui.act.fragmentContainer.FragmentContainerActivity
import pl.perski.lukasz.maraton.ui.act.training.TrainingActivity
import pl.perski.lukasz.maraton.utils.ConstStrings.Companion.EXERCISE
import pl.perski.lukasz.maraton.utils.ConstStrings.Companion.EXERCISE_TITLE
import pl.perski.lukasz.maraton.utils.ConstStrings.Companion.FRAGMENT
import pl.perski.lukasz.maraton.utils.ConstStrings.Companion.SHOW_BACK_BTN
import pl.perski.lukasz.maraton.utils.ConstStrings.Companion.TRAINING_ENTER_DATA


class CustomExpandableListAdapter(var context: Context, var expandableListView: ExpandableListView, var header: MutableList<String>, var body: MutableList<MutableList<String>>) : BaseExpandableListAdapter() {
    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_group, null)
        }

        ///////START CUSTOM TRAINING
        val btnStartCustomTraining = view!!.findViewById(R.id.btnStartCustomTraining) as Button
        btnStartCustomTraining.setOnClickListener {
            startTraining(body[groupPosition].toTypedArray())
        }
        ////////////////////
        val title = view.findViewById<TextView>(R.id.tv_title)
        title?.text = getGroup(groupPosition)
        title?.setOnClickListener {
            if (expandableListView.isGroupExpanded(groupPosition))
                expandableListView.collapseGroup(groupPosition)
            else
                expandableListView.expandGroup(groupPosition)
        }
        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return body[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return body[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_child, null)
        }
        val title = view?.findViewById<TextView>(R.id.tv_title)
        title?.text = getChild(groupPosition, childPosition)
        ///////START SINGLE EXERCISE
        title?.setOnClickListener {
            startSingleExercise(getChild(groupPosition, childPosition))
        }
        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return header.size
    }

    private fun startTraining(exercisesTitles: Array<String>) {
        val intent = Intent(context, TrainingActivity::class.java)
        intent.putExtra(TRAINING_ENTER_DATA, exercisesTitles)
        context.startActivity(intent)
    }

    private fun startSingleExercise(exercisesTitle: String) {
        val intent = Intent(context, FragmentContainerActivity::class.java)
        intent.putExtra(EXERCISE_TITLE, exercisesTitle)
        intent.putExtra(FRAGMENT, EXERCISE)
        intent.putExtra(SHOW_BACK_BTN, true)
        context.startActivity(intent)
    }
}