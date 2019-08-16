package co.thrivemobile.bpt.account

import co.thrivemobile.bpt.account.vm.SettingsViewModel
import co.thrivemobile.bpt.util.InstantExecutorExtension
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.bpt.util.observeOnce
import co.thrivemobile.repository.BptDao
import co.thrivemobile.repository.entities.Settings
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.threeten.bp.OffsetTime
import org.threeten.bp.ZoneOffset

@ExtendWith(value = [ InstantExecutorExtension::class, MockitoExtension::class ])
class SettingsViewModelTest {

    @Mock private lateinit var mockDao: BptDao

    @Test
    @DisplayName("When no name is provided in the settings then show an empty screen")
    fun noName() {
        val settings = Settings(
            userFirstName = "",
            userLastName = "",
            userEmail = ""
        )
        whenever(mockDao.getSettings()).thenReturn(NonNullLiveData(settings))

        val viewModel = SettingsViewModel(mockDao)
        viewModel.nameLiveData.observeOnce {
            assertTrue(it.isBlank())
        }
    }

    @Test
    @DisplayName("When only first name is provided in the settings then show that")
    fun firstNameOnly() {
        val settings = Settings(
            userFirstName = "First",
            userLastName = "",
            userEmail = ""
        )
        whenever(mockDao.getSettings()).thenReturn(NonNullLiveData(settings))

        val viewModel = SettingsViewModel(mockDao)
        viewModel.nameLiveData.observeOnce {
            assertTrue(it == "${settings.userFirstName} ")
        }
    }

    @Test
    @DisplayName("When only last name is provided in the settings then show that")
    fun lastNameOnly() {
        val settings = Settings(
            userFirstName = "",
            userLastName = "Last",
            userEmail = ""
        )
        whenever(mockDao.getSettings()).thenReturn(NonNullLiveData(settings))

        val viewModel = SettingsViewModel(mockDao)
        viewModel.nameLiveData.observeOnce {
            assertTrue(it == " ${settings.userLastName}")
        }
    }

    @Test
    @DisplayName("When first and last name are provided in the settings then show that")
    fun bothNames() {
        val settings = Settings(
            userFirstName = "First",
            userLastName = "Last",
            userEmail = ""
        )
        whenever(mockDao.getSettings()).thenReturn(NonNullLiveData(settings))

        val viewModel = SettingsViewModel(mockDao)
        viewModel.nameLiveData.observeOnce {
            assertTrue(it == "${settings.userFirstName} ${settings.userLastName}")
        }
    }

    @Test
    @DisplayName("When no email is given then show a blank string")
    fun noEmail() {
        val settings = Settings(
            userFirstName = "",
            userLastName = "",
            userEmail = ""
        )
        whenever(mockDao.getSettings()).thenReturn(NonNullLiveData(settings))

        val viewModel = SettingsViewModel(mockDao)
        viewModel.emailLiveData.observeOnce {
            assertTrue(it.isBlank())
        }
    }

    @Test
    @DisplayName("When email is given then show the email")
    fun emailProvided() {
        val settings = Settings(
            userFirstName = "",
            userLastName = "",
            userEmail = "sample@email.com"
        )
        whenever(mockDao.getSettings()).thenReturn(NonNullLiveData(settings))

        val viewModel = SettingsViewModel(mockDao)
        viewModel.emailLiveData.observeOnce {
            assertTrue(it == settings.userEmail)
        }
    }

    @Test
    @DisplayName("When default time values are used, then show them appropriately")
    fun defaultTimeValues() {
        val settings = Settings(
            userFirstName = "",
            userLastName = "",
            userEmail = ""
        )
        whenever(mockDao.getSettings()).thenReturn(NonNullLiveData(settings))

        val viewModel = SettingsViewModel(mockDao)
        viewModel.timeLiveData.observeOnce {
            assertTrue(it == "8am - 5pm")
        }
    }

    @Test
    @DisplayName("When a different start time is chosen, then show the times appropriately")
    fun defaultEndTime() {
        val settings = Settings(
            userFirstName = "",
            userLastName = "",
            userEmail = "sample@email.com",
            startTrackingTime = OffsetTime.of(10, 0, 0, 0, ZoneOffset.UTC)
        )
        whenever(mockDao.getSettings()).thenReturn(NonNullLiveData(settings))

        val viewModel = SettingsViewModel(mockDao)
        viewModel.timeLiveData.observeOnce {
            assertTrue(it == "10am - 5pm")
        }
    }

    @Test
    @DisplayName("When a different end time is chosen, then show the times appropriately")
    fun defaultStartTime() {
        val settings = Settings(
            userFirstName = "",
            userLastName = "",
            userEmail = "sample@email.com",
            endTrackingTime = OffsetTime.of(20, 0, 0, 0, ZoneOffset.UTC)
        )
        whenever(mockDao.getSettings()).thenReturn(NonNullLiveData(settings))

        val viewModel = SettingsViewModel(mockDao)
        viewModel.timeLiveData.observeOnce {
            assertTrue(it == "8am - 8pm")
        }
    }

    @Test
    @DisplayName("When a unique start and end time are chosen, then show the times appropriately")
    fun uniqueTimes() {
        val settings = Settings(
            userFirstName = "",
            userLastName = "",
            userEmail = "sample@email.com",
            startTrackingTime = OffsetTime.of(10, 0, 0, 0, ZoneOffset.UTC),
            endTrackingTime = OffsetTime.of(20, 0, 0, 0, ZoneOffset.UTC)
        )
        whenever(mockDao.getSettings()).thenReturn(NonNullLiveData(settings))

        val viewModel = SettingsViewModel(mockDao)
        viewModel.timeLiveData.observeOnce {
            assertTrue(it == "10am - 8pm")
        }
    }
}
