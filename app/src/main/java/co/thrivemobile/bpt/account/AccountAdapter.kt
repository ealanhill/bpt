package co.thrivemobile.bpt.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import co.thrivemobile.bpt.R
import co.thrivemobile.bpt.account.items.AccountItem
import co.thrivemobile.bpt.account.items.ProductiveTimesItem
import co.thrivemobile.bpt.account.items.SalutationItem
import co.thrivemobile.bpt.account.items.SettingsItem
import co.thrivemobile.bpt.account.vh.AccountViewHolder
import co.thrivemobile.bpt.account.vh.ProductivityTimesViewHolder
import co.thrivemobile.bpt.account.vh.SalutationViewHolder
import co.thrivemobile.bpt.account.vh.SettingsViewHolder
import co.thrivemobile.bpt.databinding.ItemProductivityListBinding
import co.thrivemobile.bpt.databinding.ItemSalutationBinding
import co.thrivemobile.bpt.databinding.ItemSettingsListBinding

class AccountAdapter(
    private val viewLifecycleOwner: LifecycleOwner
) : ListAdapter<AccountItem, AccountViewHolder<AccountItem>>(CALLBACK) {

    companion object {
        private val CALLBACK = object : DiffUtil.ItemCallback<AccountItem>() {
            override fun areItemsTheSame(oldItem: AccountItem, newItem: AccountItem): Boolean {
                return oldItem::class == newItem::class
            }

            @Suppress("USELESS_CAST")
            override fun areContentsTheSame(oldItem: AccountItem, newItem: AccountItem): Boolean {
                return when {
                    oldItem is SalutationItem && newItem is SalutationItem ->
                        oldItem as SalutationItem == newItem as SalutationItem
                    oldItem is ProductiveTimesItem && newItem is ProductiveTimesItem ->
                        oldItem as ProductiveTimesItem == newItem as ProductiveTimesItem
                    oldItem is SettingsItem && newItem is SettingsItem ->
                        oldItem as SettingsItem == newItem as SettingsItem
                    else -> false
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is SalutationItem -> R.layout.item_salutation
            is ProductiveTimesItem -> R.layout.item_productivity_list
            is SettingsItem -> R.layout.item_settings_list
            else -> throw IllegalArgumentException("Unknown item at position #$position")
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder<AccountItem> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_salutation -> setUpSalutationItem(inflater, parent)
            R.layout.item_productivity_card -> setUpProductivityList(inflater, parent)
            R.layout.item_settings_list -> setUpSettingsList(inflater, parent)
            else -> throw IllegalArgumentException("Unknown view type $viewType")
        } as AccountViewHolder<AccountItem>
    }

    override fun onBindViewHolder(holder: AccountViewHolder<AccountItem>, position: Int) {
        holder.bind(getItem(position))
    }

    private fun setUpSalutationItem(inflater: LayoutInflater, parent: ViewGroup): SalutationViewHolder {
        val binding = ItemSalutationBinding.inflate(inflater, parent, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return SalutationViewHolder(binding)
    }

    private fun setUpProductivityList(inflater: LayoutInflater, parent: ViewGroup): ProductivityTimesViewHolder {
        val binding = ItemProductivityListBinding.inflate(inflater, parent, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return ProductivityTimesViewHolder(binding, viewLifecycleOwner)
    }

    private fun setUpSettingsList(inflater: LayoutInflater, parent: ViewGroup): SettingsViewHolder {
        val binding = ItemSettingsListBinding.inflate(inflater, parent, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return SettingsViewHolder(binding)
    }
}
