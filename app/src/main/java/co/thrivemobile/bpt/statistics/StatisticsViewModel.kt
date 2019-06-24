package co.thrivemobile.bpt.statistics

import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.statistics.items.EntryItem
import co.thrivemobile.bpt.statistics.items.HourItem
import co.thrivemobile.bpt.statistics.items.SparkItem
import co.thrivemobile.bpt.statistics.items.StatisticsItem
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.repository.BptDatabase

class StatisticsViewModel(val repo: BptDatabase) : ViewModel() {

    val statisticsItemsData = NonNullLiveData<List<StatisticsItem>>(emptyList())

    init {
        statisticsItemsData.value = listOf(
            SparkItem(),
            EntryItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem()
        )
    }
}
