package co.thrivemobile.uicomponents

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Adapted from https://gist.github.com/nesquena/c715c9b22fb873b1d259
 */
abstract class SmartFragmentStatePagerAdapter<T : Fragment>(
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    private val registeredFragments = SparseArray<T>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position).also {
            @Suppress("UNCHECKED_CAST")
            registeredFragments.put(position, it as T)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

}
