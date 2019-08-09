package co.thrivemobile.bpt.info.vh

import androidx.recyclerview.widget.RecyclerView
import co.thrivemobile.bpt.databinding.ItemArticleBinding
import co.thrivemobile.bpt.info.items.ArticleItem
import co.thrivemobile.bpt.info.vm.ArticleViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class ArticleViewHolder(
    private val binding: ItemArticleBinding,
    private val openUrl: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root), InfoViewHolder<ArticleItem>, KoinComponent {

    private val articleViewModel: ArticleViewModel by inject()

    override fun bind(item: ArticleItem) {
        binding.apply {
            viewModel = articleViewModel
        }

        articleViewModel.loadItem(item)
        itemView.setOnClickListener { openUrl(item.url) }
    }
}
