package co.thrivemobile.bpt.statistics.vh

import co.thrivemobile.bpt.databinding.ItemSparkBinding
import co.thrivemobile.bpt.statistics.StatisticsSparkAdapter
import co.thrivemobile.bpt.statistics.items.SparkItem

class SparkViewHolder(
    private val binding: ItemSparkBinding
) : StatisticsViewHolder<SparkItem>(binding.root) {

    private var sparkAdapter = StatisticsSparkAdapter()

    override fun bind(item: SparkItem) {
        sparkAdapter.dayEntries = item.items.toMutableList()
        binding.apply {
            sparkView.adapter = sparkAdapter
        }
    }
}
