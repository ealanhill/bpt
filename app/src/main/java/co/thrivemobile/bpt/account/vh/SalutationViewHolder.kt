package co.thrivemobile.bpt.account.vh

import co.thrivemobile.bpt.account.items.SalutationItem
import co.thrivemobile.bpt.account.vm.SalutationViewModel
import co.thrivemobile.bpt.databinding.ItemSalutationBinding
import org.koin.core.KoinComponent
import org.koin.core.inject

class SalutationViewHolder(
    binding: ItemSalutationBinding
) : AccountViewHolder<SalutationItem>(binding.root), KoinComponent {

    private val salutationViewModel: SalutationViewModel by inject()

    init {
        binding.viewModel = salutationViewModel
    }

    override fun bind(item: SalutationItem) {
        salutationViewModel.setSalutation(item)
    }
}
