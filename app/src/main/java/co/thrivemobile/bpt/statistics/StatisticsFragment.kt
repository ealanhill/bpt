package co.thrivemobile.bpt.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.thrivemobile.bpt.databinding.FragmentStatisticsBinding
import co.thrivemobile.bpt.entry_form.OpenEntryForm
import org.koin.android.ext.android.inject

class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding
    private val statisticsViewModel: StatisticsViewModel by inject()
    private val statisticsAdapter = StatisticsAdapter { launchEntryForm() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false).apply {
            viewModel = statisticsViewModel
            lifecycleOwner = viewLifecycleOwner

            statisticsRecyclerView.apply {
                adapter = statisticsAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        statisticsViewModel.statisticsItemsData.observe(
            viewLifecycleOwner,
            Observer { statisticsAdapter.submitList(it) }
        )

        return binding.root
    }

    private fun launchEntryForm() {
        if (activity is OpenEntryForm) {
            (activity as OpenEntryForm).openEntryForm()
        }
    }
}
