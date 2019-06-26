package co.thrivemobile.bpt.statistics.vh

import co.thrivemobile.bpt.databinding.ItemHourBinding
import co.thrivemobile.bpt.statistics.items.HourItem
import co.thrivemobile.bpt.statistics.vm.HourViewModel

class HourViewHolder(val binding: ItemHourBinding) : StatisticsViewHolder<HourItem>(binding.root) {

    private val hourViewModel = HourViewModel()

    override fun bind(item: HourItem) {
        hourViewModel.setHourItem(item)
        binding.viewModel = hourViewModel
    }
}
