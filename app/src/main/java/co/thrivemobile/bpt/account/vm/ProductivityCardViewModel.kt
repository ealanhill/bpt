package co.thrivemobile.bpt.account.vm

import co.thrivemobile.bpt.account.items.ProductivityCardItem
import co.thrivemobile.bpt.util.NonNullLiveData

class ProductivityCardViewModel {

    val titleLiveData = NonNullLiveData("")
    val subTitleLiveData = NonNullLiveData("")
    val descriptionLiveData = NonNullLiveData("")

    fun displayItem(item: ProductivityCardItem) {
        item.apply {
            titleLiveData.value = title
            subTitleLiveData.value = subTitle
            descriptionLiveData.value = description
        }
    }
}
