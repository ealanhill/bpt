package co.thrivemobile.bpt.info.vh

import androidx.recyclerview.widget.RecyclerView
import co.thrivemobile.bpt.databinding.ItemTitleBinding
import co.thrivemobile.bpt.info.items.TitleItem

class TitleViewHolder(
    private val binding: ItemTitleBinding
) : RecyclerView.ViewHolder(binding.root), InfoViewHolder<TitleItem> {
    override fun bind(item: TitleItem) {

    }
}
