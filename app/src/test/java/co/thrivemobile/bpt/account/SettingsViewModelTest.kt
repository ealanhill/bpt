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
            userEmail = "",
            startTrackingTime = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            endTrackingTime = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC)
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
            userEmail = "",
            startTrackingTime = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            endTrackingTime = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC)
        )
        whenever(mockDao.getSettings()).thenReturn(NonNullLiveData(settings))

        val viewModel = SettingsViewModel(mockDao)
        viewModel.nameLiveData.observeOnce {
            assertTrue(it == "${settings.userFirstName} ")
        }
    }
}
