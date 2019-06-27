package co.thrivemobile.bpt.entry_form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.bpt.util.SingleLiveEvent
import co.thrivemobile.repository.BptDao
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class EntryFormViewModel(val repo: BptDao, now: () -> LocalDateTime) : ViewModel() {

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("h:mm a")
    }

    val timeLiveData = NonNullLiveData("0:00 AM")
    val energyLiveData = MutableLiveData<String>()
    val focusLiveData = MutableLiveData<String>()
    val motivationLiveData = MutableLiveData<String>()
    val notesLiveData = MutableLiveData<String>()

    val cancelLiveEvent = SingleLiveEvent<Boolean>()
    val saveLivedata = SingleLiveEvent<Boolean>()

    init {
        val nowString = now().format(formatter)
        timeLiveData.value = nowString
    }

    fun onCancelSelected() {
        cancelLiveEvent.value = true
    }

    fun onSaveSelected() {
        saveLivedata.value = true
    }
}
