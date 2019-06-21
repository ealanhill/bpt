package co.thrivemobile.bpt.statistics.vh

import androidx.lifecycle.Observer
import co.thrivemobile.bpt.databinding.ItemSparkBinding
import co.thrivemobile.bpt.statistics.StatisticsSparkAdapter
import co.thrivemobile.bpt.statistics.items.SparkItem
import co.thrivemobile.bpt.statistics.vm.SparkViewModel

class SparkViewHolder(
    private val binding: ItemSparkBinding
) : StatisticsViewHolder<SparkItem>(binding.root) {

    private lateinit var viewModel: SparkViewModel
    private var sparkAdapter = StatisticsSparkAdapter()

    override fun bind(item: SparkItem) {
        viewModel = SparkViewModel(item)

        binding.lifecycleOwner?.let { lifecycleOwner ->
            viewModel.dayEntriesLiveData.observe(lifecycleOwner, Observer { entries ->
                sparkAdapter.dayEntries = entries.toMutableList()
            })
        }
    }
}
