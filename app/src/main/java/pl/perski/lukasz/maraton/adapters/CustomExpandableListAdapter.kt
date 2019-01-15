package pl.perski.lukasz.maraton.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentContainer
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.ui.act.fragmentContainer.FragmentContainerActivity
import pl.perski.lukasz.maraton.ui.act.training.TrainingActivity
import pl.perski.lukasz.maraton.utils.CONST_STRINGS
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.EXERCISE
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.EXERCISE_TITLE
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.FRAGMENT
import pl.perski.lukasz.maraton.utils.CONST_STRINGS.Companion.TRAINING_ENTER_DATA


class CustomExpandableListAdapter(var context: Context, var expandableListView : ExpandableListView, var header : MutableList<String>, var body : MutableList<MutableList<String>>) : BaseExpandableListAdapter(){
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
        var convertView = convertView
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_group,null)
        }

        ///////START CUSTOM TRAINING
        val btnStartCustomTraining = convertView!!.findViewById(R.id.btnStartCustomTraining) as Button
        btnStartCustomTraining.setOnClickListener {
            startTraining(body[groupPosition].toTypedArray())
        }
        ////////////////////
        val title = convertView?.findViewById<TextView>(R.id.tv_title)
        title?.text = getGroup(groupPosition)
        title?.setOnClickListener {
            if(expandableListView.isGroupExpanded(groupPosition))
                expandableListView.collapseGroup(groupPosition)
            else
                expandableListView.expandGroup(groupPosition)
            Toast.makeText(context, getGroup(groupPosition),Toast.LENGTH_SHORT).show()
        }
        return convertView
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
        var convertView = convertView
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_child,null)
        }
        val title = convertView?.findViewById<TextView>(R.id.tv_title)
        title?.text = getChild(groupPosition,childPosition)
        ///////START SINGLE EXERCISE
        title?.setOnClickListener {
            startSingleExercise(getChild(groupPosition,childPosition))
            Toast.makeText(context, getChild(groupPosition,childPosition),Toast.LENGTH_SHORT).show()
        }
        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return header.size
    }

    private fun startTraining(exercisesTitles: Array<String>) {
        val intent = Intent(context , TrainingActivity::class.java)
        intent.putExtra(TRAINING_ENTER_DATA, exercisesTitles)
        context.startActivity(intent)
    }

    private fun startSingleExercise(exercisesTitle : String)
    {
        val intent = Intent(context , FragmentContainerActivity::class.java)
        intent.putExtra(EXERCISE_TITLE, exercisesTitle)
        intent.putExtra(FRAGMENT, EXERCISE)
        context.startActivity(intent)
    }
}