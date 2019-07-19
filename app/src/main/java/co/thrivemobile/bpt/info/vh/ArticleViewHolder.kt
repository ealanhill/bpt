package co.thrivemobile.bpt.info.vh

import androidx.recyclerview.widget.RecyclerView
import co.thrivemobile.bpt.databinding.ItemArticleBinding
import co.thrivemobile.bpt.info.items.ArticleItem

class ArticleViewHolder(
    private val binding: ItemArticleBinding
) : RecyclerView.ViewHolder(binding.root), InfoViewHolder<ArticleItem> {
    override fun bind(item: ArticleItem) {

    }
}
