package co.thrivemobile.bpt.entry_form

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.bpt.util.SingleLiveEvent
import co.thrivemobile.bpt.util.ioThread
import co.thrivemobile.repository.BptDao
import co.thrivemobile.repository.Entry
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class EntryFormViewModel(private val repo: BptDao, now: () -> OffsetDateTime) : ViewModel() {

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("h:mm a")
    }

    val timeLiveData = NonNullLiveData("0:00 AM")
    val energyLiveData = MutableLiveData<Int>()
    val focusLiveData = MutableLiveData<Int>()
    val motivationLiveData = MutableLiveData<Int>()
    val notesLiveData = MutableLiveData<String>()

    val errorLiveData = MediatorLiveData<EntryFormValidationEvent>().apply {
        value = validationEvent

        addSource(energyLiveData) { validateEnergyValue(it) }
        addSource(focusLiveData) { validateFocusValue(it) }
        addSource(motivationLiveData) { validateMotivationValue(it) }
    }

    val enableSubmit = MediatorLiveData<Boolean>().apply {
        value = false

        fun enableSubmit(): Boolean {
            return validationEvent.hasNoErrors &&
                    !(energyLiveData.value == null || energyLiveData.value == -1) &&
                    !(focusLiveData.value == null || focusLiveData.value == -1) &&
                    !(motivationLiveData.value == null || motivationLiveData.value == -1)
        }

        addSource(errorLiveData) { this.value = enableSubmit() }
    }

    val cancelLiveEvent = SingleLiveEvent<Boolean>()
    val saveLivedata = SingleLiveEvent<Boolean>()

    private val validationEvent = EntryFormValidationEvent()
    private val nowString = now().format(formatter)
    private val nowOffsetDateTime = now()

    init {
        timeLiveData.value = nowString
    }

    fun onCancelSelected() {
        cancelLiveEvent.value = true
    }

    fun onSaveSelected() {
        val newEntry = Entry(
            date = nowOffsetDateTime,
            time = nowOffsetDateTime.toOffsetTime(),
            energy = energyLiveData.value?.toInt() ?: 0,
            focus = focusLiveData.value?.toInt() ?: 0,
            motivation = motivationLiveData.value?.toInt() ?: 0,
            notes = notesLiveData.value ?: ""
        )
        ioThread {
            repo.insertEntry(newEntry)
        }
        saveLivedata.value = true
    }

    private fun validateEnergyValue(energy: Int?) {
        validationEvent.energyError = energy == null || energy !in 1..10
        errorLiveData.value = validationEvent
    }

    private fun validateFocusValue(focus: Int?) {
        validationEvent.focusError = focus == null || focus !in 1..10
        errorLiveData.value = validationEvent
    }

    private fun validateMotivationValue(motivation: Int?) {
        validationEvent.motivationError = motivation == null || motivation !in 1..10
        errorLiveData.value = validationEvent
    }
}
