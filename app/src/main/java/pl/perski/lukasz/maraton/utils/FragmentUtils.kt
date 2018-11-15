package pl.perski.lukasz.maraton.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

object FragmentUtils {

    fun replaceFragmentToActivity(fragmentManager: FragmentManager,
                                  frameId: Int, fragment: Fragment) {
        checkNotNull(fragmentManager)
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)
        transaction.commit()
    }
}

