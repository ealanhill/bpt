package co.thrivemobile.bpt.info.vm

import android.os.Build
import android.text.Html
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.info.items.ArticleItem
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.networking.Network

class ArticleViewModel(private val network: Network) : ViewModel() {

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
        network.getMetaData(articleItem.url) { metaData ->

            val title = decodeHtml(metaData.title).toString()
            val description = decodeHtml(metaData.description).toString()

            titleLiveData.value = title
            descriptionLiveData.value = description
            imageUrlLiveData.value = metaData.imageUrl
        }
    }

    private fun decodeHtml(html: String) = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        Html.fromHtml(html)
    } else {
        Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    }
}
