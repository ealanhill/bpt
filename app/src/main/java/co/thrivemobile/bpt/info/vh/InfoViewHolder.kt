package co.thrivemobile.bpt.info.vh

import co.thrivemobile.bpt.info.items.InfoItem

interface InfoViewHolder<T: InfoItem> {
    fun bind(item: T)
}
