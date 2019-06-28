package co.thrivemobile.bpt.entry_form

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.R
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.bpt.util.SingleLiveEvent
import co.thrivemobile.repository.BptDao
import co.thrivemobile.repository.Entry
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.format.DateTimeFormatter

class EntryFormViewModel(private val repo: BptDao, now: () -> LocalDateTime) : ViewModel() {

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("h:mm a")
    }

    val timeLiveData = NonNullLiveData("0:00 AM")
    val energyLiveData = MutableLiveData<String>()
    val focusLiveData = MutableLiveData<String>()
    val motivationLiveData = MutableLiveData<String>()
    val notesLiveData = MutableLiveData<String>()

    val errorLiveData = MediatorLiveData<EntryFormValidationEvent>().apply {
        value = validationEvent

        addSource(energyLiveData) { validateEnergyValue(it) }
        addSource(focusLiveData) { validateFocusValue(it) }
        addSource(motivationLiveData) { validateMotivationValue(it) }
    }

    val cancelLiveEvent = SingleLiveEvent<Boolean>()
    val saveLivedata = SingleLiveEvent<Boolean>()

    private val validationEvent = EntryFormValidationEvent()
    private val nowString = now().format(formatter)

    init {
        timeLiveData.value = nowString
    }

    fun onCancelSelected() {
        cancelLiveEvent.value = true
    }

    fun onSaveSelected() {
        val newEntry = Entry(
            date = OffsetDateTime.parse(nowString),
            time = OffsetTime.parse(nowString),
            energy = energyLiveData.value?.toInt() ?: 0,
            focus = focusLiveData.value?.toInt() ?: 0,
            motivation = motivationLiveData.value?.toInt() ?: 0,
            notes = notesLiveData.value ?: ""
        )
        repo.insertEntry(newEntry)
        saveLivedata.value = true
    }

    private fun validateEnergyValue(energy: String?) {
        validationEvent.energyErrorRes = if (energy == null || (energy.toInt() in 1..10)) {
            null
        } else {
            R.string.entry_form_number_error
        }
        errorLiveData.value = validationEvent
    }

    private fun validateFocusValue(focus: String?) {
        validationEvent.focusErrorRes = if (focus == null || (focus.toInt() in 1..10)) {
            null
        } else {
            R.string.entry_form_number_error
        }
        errorLiveData.value = validationEvent
    }

    private fun validateMotivationValue(motivation: String?) {
        validationEvent.motivationErrorRes = if (motivation == null || (motivation.toInt() in 1..10)) {
            null
        } else {
            R.string.entry_form_number_error
        }
        errorLiveData.value = validationEvent
    }
}
