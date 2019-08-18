package co.thrivemobile.bpt.account.vh

import co.thrivemobile.bpt.account.items.SettingsItem
import co.thrivemobile.bpt.account.vm.SettingsViewModel
import co.thrivemobile.bpt.databinding.ItemSettingsListBinding
import org.koin.core.KoinComponent
import org.koin.core.inject

class SettingsViewHolder(
    private val binding: ItemSettingsListBinding
) : AccountViewHolder<SettingsItem>(binding.root), KoinComponent {

    private val settingsViewModel: SettingsViewModel by inject()

    override fun bind(item: SettingsItem) {
        binding.viewModel = settingsViewModel
    }
}
