package co.thrivemobile.bpt

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import co.thrivemobile.bpt.databinding.ActivityMainBinding
import co.thrivemobile.bpt.statistics.StatisticsFragment
import co.thrivemobile.uicomponents.SmartFragmentStatePagerAdapter
import java.lang.IllegalArgumentException


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)
        binding = ActivityMainBinding.inflate(inflater).apply {
            mainViewPager.adapter = PagerAdapter(supportFragmentManager)
        }
        setContentView(binding.root)
    }

    class PagerAdapter(
        fragmentManager: FragmentManager
    ) : SmartFragmentStatePagerAdapter<Fragment>(fragmentManager) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> StatisticsFragment()
                else -> throw IllegalArgumentException("Unknown position")
            }
        }

        override fun getCount(): Int = 1

    }
}
