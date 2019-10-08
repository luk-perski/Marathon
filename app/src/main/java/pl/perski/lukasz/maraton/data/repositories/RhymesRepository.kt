package pl.perski.lukasz.maraton.data.repositories

import android.content.Context
import pl.perski.lukasz.maraton.data.db.DbWorkerThread
import pl.perski.lukasz.maraton.data.db.MarathonDB

class RhymesRepository (var context : Context) : BaseRepository() {

    fun getRhymes(recId : Int) : String?
    {
        prepareDbWorker(context)
        return mDb?.marathonDataDao()?.getRhyme(recId)
    }
}