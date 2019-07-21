package co.thrivemobile.bpt.info

import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.info.items.ArticleItem
import co.thrivemobile.bpt.info.items.HowToItem
import co.thrivemobile.bpt.info.items.TitleItem
import co.thrivemobile.bpt.info.items.WhatIsItem
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.networking.Network

class InfoViewModel(private val network: Network) : ViewModel() {

    val infoItemsData = NonNullLiveData(
        listOf(
            WhatIsItem(),
            HowToItem(),
            TitleItem(),
            ArticleItem("https://alifeofproductivity.com/calculate-biological-prime-time/"),
            ArticleItem("https://collegeinfogeek.com/track-body-energy-focus-levels/"),
            ArticleItem("https://blog.trello.com/find-productive-hours"),
            ArticleItem("https://www.thesimpledollar.com/managing-the-natural-ups-and-downs-of-your-workweek/"),
            ArticleItem("https://endpoints.elysiumhealth.com/the-complete-guide-to-the-science-of-circadian-rhythms-7b78581cbffa")
        )
    )
}
