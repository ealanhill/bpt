package co.thrivemobile.bpt.statistics.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.statistics.SparkPoint
import co.thrivemobile.repository.BptDao
import co.thrivemobile.repository.Entry
import org.threeten.bp.OffsetDateTime

class SparkViewModel(
    private val repo: BptDao,
    private val now: () -> OffsetDateTime
) : ViewModel() {

    private val entriesLive: LiveData<List<Entry>>
        get() {
            val currentDay = now()
            return repo.getEntriesForDay(currentDay)
        }

    val entriesMediated = MediatorLiveData<List<SparkPoint>>().apply {
        this.value = mutableListOf()

        addSource(entriesLive) { databaseEntries ->
            val entries = this.value?.toMutableList() ?: mutableListOf()
            databaseEntries.forEach { addEntryToList(it, entries) }
            this.value = entries
        }
    }

    private fun addEntryToList(entry: Entry, entries: MutableList<SparkPoint>) {
        val average = (entry.energy + entry.focus + entry.motivation) / 3f

        val hour = entry.time?.hour ?: 0
        val minute = entry.time?.minute ?: 0
        val percentOfHour = minute / 60f
        val xCoordinate = hour.toFloat() + percentOfHour

        val sparkPoint = SparkPoint(xCoordinate, average)

        if (!entries.contains(sparkPoint)) {
            entries.add(sparkPoint)
        }
    }
}
