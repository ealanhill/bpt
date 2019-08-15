package co.thrivemobile.bpt.account.vm

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import co.thrivemobile.repository.BptDao
import org.threeten.bp.format.DateTimeFormatter

class SettingsViewModel(private val dao: BptDao) : ViewModel() {

    private val timeFormatter = DateTimeFormatter.ofPattern("hh a")

    val nameLiveData = MediatorLiveData<String>().apply {
        value = ""

        addSource(dao.getSettings()) { settings ->
            value = "${settings.userFirstName} ${settings.userLastName}"
        }
    }

    val emailLiveData = MediatorLiveData<String>().apply {
        value = ""

        addSource(dao.getSettings()) { settings ->
            value = settings.userEmail
        }
    }

    val timeLiveData = MediatorLiveData<String>().apply {
        value = ""

        addSource(dao.getSettings()) { settings ->
            val startTimeString = timeFormatter.format(settings.startTrackingTime)
            val endTimeString = timeFormatter.format(settings.endTrackingTime)

            value = "$startTimeString $endTimeString"
        }
    }
}
