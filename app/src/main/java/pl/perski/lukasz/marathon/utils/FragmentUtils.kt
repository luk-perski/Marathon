package pl.perski.lukasz.marathon.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import pl.perski.lukasz.marathon.data.model.ExerciseData
import pl.perski.lukasz.marathon.ui.fragments.ExerciseBaseFragment
import java.io.Serializable

object FragmentUtils {


    fun replaceFragmentToActivity(fragmentManager: FragmentManager,
                                  frameId: Int, fragment : ExerciseBaseFragment?) {
        checkNotNull(fragmentManager)

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)
        //TODO: sprawdź, czy poniższa linijka jest przydatna
       // transaction.addToBackStack(null)
        transaction.commit()
    }

    }

