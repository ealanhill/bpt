package co.thrivemobile.bpt.info

import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.info.items.ArticleItem
import co.thrivemobile.bpt.info.items.HowToItem
import co.thrivemobile.bpt.info.items.InfoItem
import co.thrivemobile.bpt.info.items.TitleItem
import co.thrivemobile.bpt.info.items.WhatIsItem
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.networking.Network

class InfoViewModel(articles: Array<String>) : ViewModel() {

    val infoItemsData = NonNullLiveData(emptyList<InfoItem>())

    private val infoItemsList = mutableListOf(
        WhatIsItem(),
        HowToItem(),
        TitleItem()
    )

    init {
        articles.forEach { infoItemsList.add(ArticleItem(it)) }
        infoItemsData.value = infoItemsList
    }
}
