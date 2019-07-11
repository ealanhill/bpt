package co.thrivemobile.bpt.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.statistics.items.EntryItem
import co.thrivemobile.bpt.statistics.items.HourItem
import co.thrivemobile.bpt.statistics.items.SparkItem
import co.thrivemobile.bpt.statistics.items.StatisticsItem
import co.thrivemobile.repository.BptDao
import co.thrivemobile.repository.Entry
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class StatisticsViewModel(val repo: BptDao, val now: () -> OffsetDateTime) : ViewModel() {

    private val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")

    private val entriesLive: LiveData<List<Entry>>
        get() {
            val currentDay = now()
            return repo.getEntriesForDay(currentDay)
        }
    val statisticsItemsLiveData = MediatorLiveData<List<StatisticsItem>>().apply {
        this.value = listOf(
            SparkItem(),
            EntryItem()
        )

        addSource(entriesLive) { entries ->
            val items = this.value?.toMutableList() ?: mutableListOf()
            val hourItems = entries.map { mapEntryToHourItem(it) }
            val intersection = hourItems.intersect(items)
            val hourItemsToAdd = hourItems.minus(intersection)
            items.addAll(hourItemsToAdd)
            this.value = items
        }
    }

    private fun mapEntryToHourItem(entry: Entry) = HourItem(
        hour = timeFormatter.format(entry.time),
        energy = entry.energy,
        focus = entry.focus,
        motivation = entry.motivation
    )
}
