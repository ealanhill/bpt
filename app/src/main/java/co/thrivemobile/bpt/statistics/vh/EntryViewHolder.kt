package co.thrivemobile.bpt.statistics.vh

import co.thrivemobile.bpt.databinding.ItemEntryBinding
import co.thrivemobile.bpt.statistics.items.EntryItem

class EntryViewHolder(
    val binding: ItemEntryBinding,
    private val onClick: () -> Unit
) : StatisticsViewHolder<EntryItem>(binding.root) {

    override fun bind(item: EntryItem) {
        itemView.setOnClickListener { onClick() }
    }
}
