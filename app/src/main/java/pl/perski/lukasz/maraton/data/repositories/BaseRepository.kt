package pl.perski.lukasz.maraton.data.repositories

import android.content.Context
import pl.perski.lukasz.maraton.data.db.DbWorkerThread
import pl.perski.lukasz.maraton.data.db.MarathonDB


abstract class BaseRepository {

    var mDb: MarathonDB? = null
    lateinit var mDbWorkerThread: DbWorkerThread

    fun prepareDbWorker(context: Context) {
        mDb = MarathonDB.getInstance(context)
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
    }
}

