package co.thrivemobile.bpt.info

import co.thrivemobile.bpt.R
import co.thrivemobile.bpt.info.items.ArticleItem
import co.thrivemobile.bpt.info.items.HowToItem
import co.thrivemobile.bpt.info.items.TitleItem
import co.thrivemobile.bpt.info.items.WhatIsItem
import co.thrivemobile.bpt.util.InstantExecutorExtension
import co.thrivemobile.bpt.util.observeOnce
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [ InstantExecutorExtension::class, MockitoExtension::class ])
class InfoViewModelTest {

    private val articles = arrayOf(
        "https://alifeofproductivity.com/calculate-biological-prime-time/",
        "https://collegeinfogeek.com/track-body-energy-focus-levels/",
        "https://blog.trello.com/find-productive-hours",
        "https://www.thesimpledollar.com/managing-the-natural-ups-and-downs-of-your-workweek/",
        "https://endpoints.elysiumhealth.com/the-complete-guide-to-the-science-of-circadian-rhythms-7b78581cbffa"
    )

    private val defaultList = listOf(
        WhatIsItem(),
        HowToItem(),
        TitleItem(R.string.articles),
        ArticleItem("https://alifeofproductivity.com/calculate-biological-prime-time/"),
        ArticleItem("https://collegeinfogeek.com/track-body-energy-focus-levels/"),
        ArticleItem("https://blog.trello.com/find-productive-hours"),
        ArticleItem("https://www.thesimpledollar.com/managing-the-natural-ups-and-downs-of-your-workweek/"),
        ArticleItem("https://endpoints.elysiumhealth.com/the-complete-guide-to-the-science-of-circadian-rhythms-7b78581cbffa")
    )

    private lateinit var viewModel: InfoViewModel

    @Test
    @DisplayName("When the page first loads, the items are displayed correctly")
    fun defaultItems() {
        viewModel = InfoViewModel(articles)
        viewModel.infoItemsData.observeOnce {
            assertTrue(it == defaultList)
        }
    }
}
