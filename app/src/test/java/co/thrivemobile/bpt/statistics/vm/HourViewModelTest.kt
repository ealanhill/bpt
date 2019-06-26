package co.thrivemobile.bpt.statistics.vm

import co.thrivemobile.bpt.R
import co.thrivemobile.bpt.statistics.items.HourItem
import co.thrivemobile.bpt.util.InstantExecutorExtension
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [ InstantExecutorExtension::class, MockitoExtension::class ])
class HourViewModelTest {

    @InjectMocks lateinit var viewModel: HourViewModel

    @Test
    @DisplayName("When we've instantiated the HourViewModel, ensure the default values are correct")
    fun verifyStartValues() {
        assertTrue(viewModel.timeLiveData.value == "0:00")
        assertTrue(viewModel.energyLiveData.value == "0")
        assertTrue(viewModel.energyTextColorData.value == R.color.grey)
        assertTrue(viewModel.focusLiveData.value == "0")
        assertTrue(viewModel.focusTextColorData.value == R.color.grey)
        assertTrue(viewModel.motivationLiveData.value == "0")
        assertTrue(viewModel.motivationTextColorData.value == R.color.grey)
    }

    @Test
    @DisplayName("When energy, focus, and motivation are greater than 5, then show correct color")
    fun greaterThanFive() {
        val item = HourItem(
            hour = "11:00am",
            energy = 8,
            focus = 8,
            motivation = 8)
        viewModel.setHourItem(item)
        assertTrue(viewModel.timeLiveData.value == "11:00am")
        assertTrue(viewModel.energyLiveData.value == "8")
        assertTrue(viewModel.energyTextColorData.value == R.color.colorPrimary)
        assertTrue(viewModel.focusLiveData.value == "8")
        assertTrue(viewModel.focusTextColorData.value == R.color.colorPrimary)
        assertTrue(viewModel.motivationLiveData.value == "8")
        assertTrue(viewModel.motivationTextColorData.value == R.color.colorPrimary)
    }

    @Test
    @DisplayName("When energy, focus, and motivation are less than 5, then show correct color")
    fun lessThanFive() {
        val item = HourItem(
            hour = "11:00am",
            energy = 3,
            focus = 3,
            motivation = 3)
        viewModel.setHourItem(item)
        assertTrue(viewModel.timeLiveData.value == "11:00am")
        assertTrue(viewModel.energyLiveData.value == "3")
        assertTrue(viewModel.energyTextColorData.value == R.color.midwinter_fire)
        assertTrue(viewModel.focusLiveData.value == "3")
        assertTrue(viewModel.focusTextColorData.value == R.color.midwinter_fire)
        assertTrue(viewModel.motivationLiveData.value == "3")
        assertTrue(viewModel.motivationTextColorData.value == R.color.midwinter_fire)
    }

    @Test
    @DisplayName("When energy, focus, and motivation are 5, then show correct color")
    fun equalToFive() {
        val item = HourItem(
            hour = "11:00am",
            energy = 5,
            focus = 5,
            motivation = 5
        )
        viewModel.setHourItem(item)
        assertTrue(viewModel.timeLiveData.value == "11:00am")
        assertTrue(viewModel.energyLiveData.value == "5")
        assertTrue(viewModel.energyTextColorData.value == R.color.midwinter_fire)
        assertTrue(viewModel.focusLiveData.value == "5")
        assertTrue(viewModel.focusTextColorData.value == R.color.midwinter_fire)
        assertTrue(viewModel.motivationLiveData.value == "5")
        assertTrue(viewModel.motivationTextColorData.value == R.color.midwinter_fire)
    }
}
