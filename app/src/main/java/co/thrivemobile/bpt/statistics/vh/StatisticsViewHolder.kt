package co.thrivemobile.bpt.statistics.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import co.thrivemobile.bpt.statistics.items.StatisticsItem

abstract class StatisticsViewHolder<T: StatisticsItem>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T)
}
