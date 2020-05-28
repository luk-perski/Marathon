package pl.perski.lukasz.maraton.data.repositories

import android.content.Context

class RhymesRepository(var context: Context) : BaseRepository() {

    fun getRhymes(recId: Int): String? {
        prepareDbWorker(context)
        return mDb?.marathonDataDao()?.getRhyme(recId)
    }
}