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

class StatisticsViewModel(
    private val repo: BptDao,
    private val now: () -> OffsetDateTime
) : ViewModel() {

    companion object {
        private val STARTING_LIST = listOf(
            SparkItem(),
            EntryItem()
        )
    }

    private val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")

    private val entriesLive: LiveData<List<Entry>>
        get() {
            val currentDay = now()
            return repo.getEntriesForDay(currentDay)
        }
    val statisticsItemsLiveData = MediatorLiveData<List<StatisticsItem>>().apply {
        this.value = STARTING_LIST

        addSource(entriesLive) { entries ->
            val items = STARTING_LIST.toMutableList()
            val hourItems = entries.map { mapEntryToHourItem(it) }
            items.addAll(hourItems)
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
