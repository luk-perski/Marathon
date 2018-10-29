package pl.perski.lukasz.marathon.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import pl.perski.lukasz.marathon.R
import pl.perski.lukasz.marathon.data.model.Exercise

class ExercisesListAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises_list)
//        val dataProvider = DataProvider()
//        var exercisesList = dataProvider.streamingArray(applicationContext)
//        var exercisesList = Gson().fromJson(applicationContext.assets.open("exercises.json").bufferedReader(), Array<Exercise>::class.java).asList()
//        val lv = findViewById(R.id.lv) as ListView
//        lv.adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, exercisesList)
    }
}
