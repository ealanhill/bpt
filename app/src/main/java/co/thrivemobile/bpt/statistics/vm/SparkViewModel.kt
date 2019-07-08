package co.thrivemobile.bpt.statistics.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
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

    val entriesMediated = MediatorLiveData<List<Float>>().apply {
        this.value = mutableListOf()

        addSource(entriesLive) { newEntries ->
            val items: List<Float> = newEntries.map { entry ->
                (entry.energy + entry.focus + entry.motivation) / 3f
            }
            this.value = items
        }
    }
}
