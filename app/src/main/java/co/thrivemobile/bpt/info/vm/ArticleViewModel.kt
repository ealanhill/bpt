package co.thrivemobile.bpt.info.vm

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.info.items.ArticleItem
import co.thrivemobile.bpt.repository.Repository
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.networking.Network

class ArticleViewModel(
    private val repository: Repository,
    private val decodeHtml: (String) -> String
) : ViewModel() {

    val titleLiveData = NonNullLiveData("")
    val descriptionLiveData = NonNullLiveData("")
    val imageUrlLiveData = NonNullLiveData("")

    val showPlaceholderLiveData = MediatorLiveData<Boolean>().apply {
        this.value = true

        fun showPlaceholder() {
            this.value = titleLiveData.value.isEmpty() &&
                    descriptionLiveData.value.isEmpty()
        }

        addSource(titleLiveData) { showPlaceholder() }
        addSource(descriptionLiveData) { showPlaceholder() }
    }

    fun loadItem(articleItem: ArticleItem) {
        val metadata = repository.getArticleByUrl(articleItem.url).metadata
        titleLiveData.value = decodeHtml(metadata.title)
        descriptionLiveData.value = decodeHtml(metadata.description)
        imageUrlLiveData.value = metadata.imageUrl
    }
}
