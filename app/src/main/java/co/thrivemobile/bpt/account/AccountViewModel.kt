package co.thrivemobile.bpt.account

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.account.items.AccountItem
import co.thrivemobile.bpt.account.items.ProductiveTimesItem
import co.thrivemobile.bpt.account.items.SalutationItem
import co.thrivemobile.bpt.account.items.SettingsItem
import co.thrivemobile.repository.BptDao
import co.thrivemobile.repository.entities.Settings

class AccountViewModel(private val bptDao: BptDao) : ViewModel() {

    val accountItemsLiveData = MediatorLiveData<List<AccountItem>>().apply {
        value = emptyList()

        addSource(bptDao.getSettings()) { settings ->
            val salutationItem = createSalutationItem(settings)
            val productivityItem = createProductiveTimesItem()
            val settingsItem = createSettingsItem()

            value = listOf(salutationItem, productivityItem, settingsItem)
        }
    }

    private fun createSalutationItem(settings: Settings) =
        if (settings.userFirstName.isEmpty()) {
            SalutationItem("")
        } else {
            SalutationItem(settings.userFirstName)
        }

    private fun createProductiveTimesItem(): ProductiveTimesItem {
        return ProductiveTimesItem(emptyList())
    }

    private fun createSettingsItem(): SettingsItem {
        return SettingsItem()
    }
}
