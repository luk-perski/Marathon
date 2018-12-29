package pl.perski.lukasz.maraton.data.repositories

import android.content.Context

class HintsRepository (var context : Context) : BaseRepository() {

    fun getAll(recId : Int) : List<String>?
    {
        prepareDbWorker(context)
        return if (recId == 36){
            mDb?.marathonDataDao()?.getAllTwoVowelsWords()
        }
        else{
            mDb?.marathonDataDao()?.getAllConstCombWords()
        }
    }


}