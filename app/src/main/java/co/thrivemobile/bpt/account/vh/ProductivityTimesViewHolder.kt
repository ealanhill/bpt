package co.thrivemobile.bpt.account.vh

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import co.thrivemobile.bpt.R
import co.thrivemobile.bpt.account.ProductivityCardAdapter
import co.thrivemobile.bpt.account.items.ProductiveTimesItem
import co.thrivemobile.bpt.databinding.ItemProductivityListBinding
import co.thrivemobile.bpt.util.MarginItemDecorator

class ProductivityTimesViewHolder(
    binding: ItemProductivityListBinding,
    viewLifecycleOwner: LifecycleOwner
) : AccountViewHolder<ProductiveTimesItem>(binding.root) {

    private val productivityCardAdapter = ProductivityCardAdapter(viewLifecycleOwner)

    init {
        binding.productivityCardList.apply {
            adapter = productivityCardAdapter
            addItemDecoration(MarginItemDecorator(R.dimen.half_margin))
        }
    }

    override fun bind(item: ProductiveTimesItem) {
        productivityCardAdapter.submitList(item.productivityCards)
    }

}
