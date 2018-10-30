package pl.perski.lukasz.marathon.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import android.widget.ListView
import pl.perski.lukasz.marathon.DbWorkerThread
import pl.perski.lukasz.marathon.R
import pl.perski.lukasz.marathon.data.db.ExercisesDB
import pl.perski.lukasz.marathon.data.model.ExerciseData


class ExercisesListAct : AppCompatActivity() {

    private var mDb : ExercisesDB? = null
    private lateinit var mDbWorkerThread: DbWorkerThread
    var exerciseData : List<ExerciseData> ? = null
    private val mUiHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises_list)
        mDb = ExercisesDB.getInstance(this)
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        fetchExerciseDataFromDb()
        val lv = findViewById(R.id.lv) as ListView
        if (exerciseData !== null)
        lv.adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, exerciseData)
    }


    private fun fetchExerciseDataFromDb() {
//        val task = Runnable {
            exerciseData =
                    mDb?.exerciseDataDao()?.getAll()
//            mUiHandler.post({
//                if (exerciseData == null || exerciseData?.size == 0) {
//                    Toast.makeText(applicationContext, "No data in cache..!!", Toast.LENGTH_SHORT).show()
//                }
//
//            })
//        }
//        mDbWorkerThread.postTask(task)
    }

    override fun onDestroy() {
        ExercisesDB.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }
}
