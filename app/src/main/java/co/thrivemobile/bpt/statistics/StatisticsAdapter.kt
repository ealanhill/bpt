package co.thrivemobile.bpt.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import co.thrivemobile.bpt.R
import co.thrivemobile.bpt.databinding.ItemEntryBinding
import co.thrivemobile.bpt.databinding.ItemHourBinding
import co.thrivemobile.bpt.databinding.ItemSparkBinding
import co.thrivemobile.bpt.statistics.items.EntryItem
import co.thrivemobile.bpt.statistics.items.HourItem
import co.thrivemobile.bpt.statistics.items.SparkItem
import co.thrivemobile.bpt.statistics.items.StatisticsItem
import co.thrivemobile.bpt.statistics.vh.EntryViewHolder
import co.thrivemobile.bpt.statistics.vh.HourViewHolder
import co.thrivemobile.bpt.statistics.vh.SparkViewHolder
import co.thrivemobile.bpt.statistics.vh.StatisticsViewHolder

class StatisticsAdapter
    : ListAdapter<StatisticsItem, StatisticsViewHolder<StatisticsItem>>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StatisticsItem>() {
            override fun areItemsTheSame(oldItem: StatisticsItem, newItem: StatisticsItem): Boolean {
                return (oldItem is SparkItem && newItem is SparkItem) ||
                        (oldItem is EntryItem && newItem is EntryItem) ||
                        (oldItem is HourItem && newItem is HourItem)
            }

            @Suppress("USELESS_CAST")
            override fun areContentsTheSame(oldItem: StatisticsItem, newItem: StatisticsItem): Boolean {
                return when {
                    oldItem is SparkItem && newItem is SparkItem -> {
                        (oldItem as SparkItem) == (newItem as SparkItem)
                    }
                    oldItem is EntryItem && newItem is EntryItem -> {
                        (oldItem as EntryItem) == (newItem as EntryItem)
                    }
                    oldItem is HourItem && newItem is HourItem -> {
                        (oldItem as HourItem) == (newItem as HourItem)
                    }
                    else -> false
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatisticsViewHolder<StatisticsItem> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_spark -> createSparkViewHolder(inflater, parent)
            R.layout.item_entry -> createEntryViewHolder(inflater, parent)
            R.layout.item_hour -> createHourViewHolder(inflater, parent)
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        } as StatisticsViewHolder<StatisticsItem>
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SparkItem -> R.layout.item_spark
            is EntryItem -> R.layout.item_entry
            is HourItem -> R.layout.item_hour
            else -> throw IllegalArgumentException("Unknown item at position #$position")
        }
    }

    override fun onBindViewHolder(holder: StatisticsViewHolder<StatisticsItem>, position: Int) {
        holder.bind(getItem(position))
    }

    private fun createSparkViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): SparkViewHolder {
        val binding = ItemSparkBinding.inflate(inflater, parent, false)
        return SparkViewHolder(binding)
    }

    private fun createEntryViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): EntryViewHolder {
        val binding = ItemEntryBinding.inflate(inflater, parent, false)
        return EntryViewHolder(binding)
    }

    private fun createHourViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): HourViewHolder {
        val binding = ItemHourBinding.inflate(inflater, parent, false)
        return HourViewHolder(binding)
    }
}
