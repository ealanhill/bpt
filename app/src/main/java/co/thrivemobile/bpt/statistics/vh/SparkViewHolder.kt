package co.thrivemobile.bpt.statistics.vh

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import co.thrivemobile.bpt.databinding.ItemSparkBinding
import co.thrivemobile.bpt.statistics.StatisticsSparkAdapter
import co.thrivemobile.bpt.statistics.items.SparkItem
import co.thrivemobile.bpt.statistics.vm.SparkViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class SparkViewHolder(
    private val binding: ItemSparkBinding,
    private val lifecycleOwner: LifecycleOwner
) : StatisticsViewHolder<SparkItem>(binding.root), KoinComponent {

    private var sparkAdapter = StatisticsSparkAdapter()

    private val sparkViewModel: SparkViewModel by inject()

    override fun bind(item: SparkItem) {
        sparkViewModel.entriesMediated.observe(lifecycleOwner, Observer { entries ->
            sparkAdapter.averages = entries.toMutableList()
        })

        binding.apply {
            viewModel = sparkViewModel
            sparkView.adapter = sparkAdapter
        }
    }
}
