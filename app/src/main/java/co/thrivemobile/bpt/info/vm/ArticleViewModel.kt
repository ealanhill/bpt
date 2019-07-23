package co.thrivemobile.bpt.info.vm

import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.info.items.ArticleItem
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.networking.Network

class ArticleViewModel(private val network: Network) : ViewModel() {

    val titleLiveData = NonNullLiveData("")
    val descriptionLiveData = NonNullLiveData("")
    val imageUrlLiveData = NonNullLiveData("")

    fun loadItem(articleItem: ArticleItem) {
        network.getMetaData(articleItem.url) { metaData ->
            titleLiveData.value = metaData.title
            descriptionLiveData.value = metaData.description
            imageUrlLiveData.value = metaData.imageUrl
        }
    }
}
