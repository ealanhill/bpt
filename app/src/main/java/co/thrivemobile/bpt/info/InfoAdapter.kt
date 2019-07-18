package co.thrivemobile.bpt.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.thrivemobile.bpt.R
import co.thrivemobile.bpt.databinding.ItemArticleBinding
import co.thrivemobile.bpt.databinding.ItemHowToBinding
import co.thrivemobile.bpt.databinding.ItemTitleBinding
import co.thrivemobile.bpt.databinding.ItemWhatIsBinding
import co.thrivemobile.bpt.info.items.ArticleItem
import co.thrivemobile.bpt.info.items.HowToItem
import co.thrivemobile.bpt.info.items.InfoItem
import co.thrivemobile.bpt.info.items.TitleItem
import co.thrivemobile.bpt.info.items.WhatIsItem
import co.thrivemobile.bpt.info.vh.ArticleViewHolder
import co.thrivemobile.bpt.info.vh.HowToViewHolder
import co.thrivemobile.bpt.info.vh.InfoViewHolder
import co.thrivemobile.bpt.info.vh.TitleViewHolder
import co.thrivemobile.bpt.info.vh.WhatIsViewHolder

class InfoAdapter(
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<InfoItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<InfoItem>() {
            override fun areItemsTheSame(oldItem: InfoItem, newItem: InfoItem): Boolean {
                return oldItem::class == newItem::class
            }

            @Suppress("USELESS_CAST")
            override fun areContentsTheSame(oldItem: InfoItem, newItem: InfoItem): Boolean {
                return when {
                    (oldItem is WhatIsItem && newItem is WhatIsItem) -> {
                        (oldItem as WhatIsItem) == (newItem as WhatIsItem)
                    }
                    (oldItem is HowToItem && newItem is HowToItem) -> {
                        (oldItem as HowToItem) == (newItem as HowToItem)
                    }
                    (oldItem is TitleItem && newItem is TitleItem) -> {
                        (oldItem as TitleItem) == (newItem as TitleItem)
                    }
                    (oldItem is ArticleItem && newItem is ArticleItem) -> {
                        (oldItem as ArticleItem) == (newItem as ArticleItem)
                    }
                    else -> false
                }
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is WhatIsItem -> R.layout.item_what_is
            is HowToItem -> R.layout.item_how_to
            is TitleItem -> R.layout.item_title
            is ArticleItem -> R.layout.item_article
            else -> throw IllegalArgumentException("Unknown item at position #$position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_what_is -> createWhatIsViewHolder(inflater, parent)
            R.layout.item_how_to -> createHowToViewHolder(inflater, parent)
            R.layout.item_title -> createTitleViewHolder(inflater, parent)
            R.layout.item_article -> createArticleViewHolder(inflater, parent)
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as InfoViewHolder<InfoItem>).bind(getItem(position) as InfoItem)
    }

    private fun createWhatIsViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): WhatIsViewHolder {
        val binding = ItemWhatIsBinding.inflate(inflater, parent, false)
        return WhatIsViewHolder(binding)
    }

    private fun createHowToViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): HowToViewHolder {
        val binding = ItemHowToBinding.inflate(inflater, parent, false)
        return HowToViewHolder(binding)
    }

    private fun createTitleViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): TitleViewHolder {
        val binding = ItemTitleBinding.inflate(inflater, parent, false)
        return TitleViewHolder(binding)
    }

    private fun createArticleViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding)
    }
}
