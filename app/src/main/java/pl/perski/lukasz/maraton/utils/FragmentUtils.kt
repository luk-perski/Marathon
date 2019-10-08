package pl.perski.lukasz.maraton.utils


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

