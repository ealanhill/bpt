package co.thrivemobile.bpt.account.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.account.items.SalutationItem
import co.thrivemobile.bpt.util.NonNullLiveData

class SalutationViewModel : ViewModel() {

    val salutationLiveData = NonNullLiveData("")

    fun setSalutation(item: SalutationItem, context: Context) {
        salutationLiveData.value = if (item.userName == null) {
            context.getString(item.salutationItemId)
        } else {
            context.getString(item.salutationItemId, item.userName)
        }
    }
}
