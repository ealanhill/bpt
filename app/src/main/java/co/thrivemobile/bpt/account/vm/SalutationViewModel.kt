package co.thrivemobile.bpt.account.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.R
import co.thrivemobile.bpt.account.items.SalutationItem

class SalutationViewModel : ViewModel() {

    val salutationResLiveData = MutableLiveData<Int>()
    val userNameLiveData = MutableLiveData<String>()

    fun setSalutation(item: SalutationItem) {
        salutationResLiveData.value = if (item.userName == null) {
            R.string.generic_salutation
        } else {
            R.string.specific_salutation
        }
        userNameLiveData.value = item.userName
    }
}
