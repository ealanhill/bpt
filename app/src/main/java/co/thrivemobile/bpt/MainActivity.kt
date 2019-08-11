package co.thrivemobile.bpt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import co.thrivemobile.bpt.account.AccountFragment
import co.thrivemobile.bpt.databinding.ActivityMainBinding
import co.thrivemobile.bpt.entry_form.EntryFormDialogFragment
import co.thrivemobile.bpt.entry_form.OpenEntryForm
import co.thrivemobile.bpt.info.InfoFragment
import co.thrivemobile.bpt.statistics.StatisticsFragment
import co.thrivemobile.bpt.web.OpenWebView
import co.thrivemobile.uicomponents.SmartFragmentStatePagerAdapter


class MainActivity : AppCompatActivity(), OpenEntryForm, OpenWebView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)
        pagerAdapter = PagerAdapter(supportFragmentManager)
        binding = ActivityMainBinding.inflate(inflater).apply {
            mainViewPager.adapter = pagerAdapter
            mainBottomNavigation.setOnNavigationItemSelectedListener {
                onMenuItemSelected(it, mainViewPager)
            }
        }
        setContentView(binding.root)
    }

    override fun openEntryForm() {
        val entryDialogFragment = EntryFormDialogFragment()
        entryDialogFragment.show(supportFragmentManager, "Entry Form")
    }

    private fun onMenuItemSelected(menuItem: MenuItem, viewPager: ViewPager): Boolean {
        val currentItem = when (menuItem.itemId) {
            R.id.menu_chart -> 0
            R.id.menu_info -> 1
            else -> -1
        }

        return if (currentItem == -1) {
            false
        } else {
            viewPager.currentItem = currentItem
            true
        }
    }

    class PagerAdapter(
        fragmentManager: FragmentManager
    ) : SmartFragmentStatePagerAdapter<Fragment>(fragmentManager) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> StatisticsFragment()
                1 -> InfoFragment()
                2 -> AccountFragment()
                else -> throw IllegalArgumentException("Unknown position")
            }
        }

        override fun getCount(): Int = 2

    }
}
