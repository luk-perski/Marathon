package pl.perski.lukasz.maraton.utils

import android.app.Fragment
import android.app.FragmentManager
import com.google.common.base.Preconditions.checkNotNull

object FragmentUtils {

    fun replaceFragmentToActivity(fragmentManager: FragmentManager,
                                  frameId: Int, fragment: Fragment) {
        checkNotNull(fragmentManager)
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)
        transaction.commit()
    }
}

