package pl.perski.lukasz.maraton.adapters


import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.goodiebag.horizontalpicker.HorizontalPicker
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.ui.act.training.TrainingActivity
import pl.perski.lukasz.maraton.ui.act.training.TrainingActivityPresenter
import spencerstudios.com.fab_toast.FabToast

class FragmentDialog : DialogFragment() {

    private var content: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = arguments!!.getString("content")

        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style, theme)
    }

    // Override the Fragment.onAttach() method to instantiate the
    // NoticeDialogListener
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.layout_mood_dialog, container, false)
        val picker = view.findViewById<HorizontalPicker>(R.id.moodPicker)
        val btnEnd = view.findViewById<View>(R.id.btnEnd) as Button

        val imageItems = ArrayList<HorizontalPicker.DrawableItem>()
        imageItems.add(HorizontalPicker.DrawableItem(R.drawable.ic_mood_red_64dp))
        imageItems.add(HorizontalPicker.DrawableItem(R.drawable.ic_mood_orange_64dp))
        imageItems.add(HorizontalPicker.DrawableItem(R.drawable.ic_mood_green_64dp))
        picker.items = imageItems as List<HorizontalPicker.PickerItem>?



        btnEnd.setOnClickListener {

            if (picker.selectedIndex <0)
            {
                FabToast.makeText(context, context!!.resources.getString(R.string.no_mood_selected), FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_CENTER).show()
            }

            else {
                var act = activity as TrainingActivity
                act.saveDataAndFinish(picker.selectedIndex)
                dismiss()
            }
        }

        return view
    }

    companion object {
        fun newInstance(): FragmentDialog {
            val f = FragmentDialog()

            // Supply num input as an argument.
            val args = Bundle()
            // args.putString("content", content)
            f.arguments = args

            return f
        }
    }
}
