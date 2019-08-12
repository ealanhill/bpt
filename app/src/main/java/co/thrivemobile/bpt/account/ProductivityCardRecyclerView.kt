package co.thrivemobile.bpt.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.thrivemobile.bpt.account.items.ProductivityCardItem
import co.thrivemobile.bpt.account.vm.ProductivityCardViewModel
import co.thrivemobile.bpt.databinding.ItemProductivityCardBinding

class ProductivityCardRecyclerView(
    private val viewLifecycleOwner: LifecycleOwner
) : ListAdapter<ProductivityCardItem, ProductivityCardRecyclerView.ProductivityCardViewHolder>(
    CALLBACK
) {

    companion object {
        private val CALLBACK = object : DiffUtil.ItemCallback<ProductivityCardItem>() {
            override fun areItemsTheSame(
                oldItem: ProductivityCardItem,
                newItem: ProductivityCardItem
            ): Boolean {
                return oldItem::class == newItem::class
            }

            override fun areContentsTheSame(
                oldItem: ProductivityCardItem,
                newItem: ProductivityCardItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductivityCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductivityCardBinding.inflate(inflater, parent, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return ProductivityCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductivityCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductivityCardViewHolder(
        binding: ItemProductivityCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val productivityCardViewModel = ProductivityCardViewModel()

        init {
            binding.viewModel = productivityCardViewModel
        }

        fun bind(item: ProductivityCardItem) {
            productivityCardViewModel.displayItem(item)
        }
    }
}
