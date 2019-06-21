package co.thrivemobile.bpt.statistics.vm

import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.statistics.items.SparkItem
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.repository.Entry
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.ZoneOffset

class SparkViewModel(item: SparkItem) : ViewModel() {

    val dayEntriesLiveData = NonNullLiveData<List<Entry>>(emptyList())

    init {
        dayEntriesLiveData.value = item.items
    }
}
