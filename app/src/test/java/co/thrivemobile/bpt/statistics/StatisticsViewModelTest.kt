package co.thrivemobile.bpt.statistics

import co.thrivemobile.bpt.statistics.items.EntryItem
import co.thrivemobile.bpt.statistics.items.HourItem
import co.thrivemobile.bpt.statistics.items.SparkItem
import co.thrivemobile.bpt.util.InstantExecutorExtension
import co.thrivemobile.repository.BptDatabase
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [ InstantExecutorExtension::class, MockitoExtension::class ])
class StatisticsViewModelTest {

    companion object {
        private val DEFAULT_LIST = listOf(
            SparkItem(),
            EntryItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem(),
            HourItem()
        )
    }

    @Mock lateinit var mockRepo: BptDatabase
    @InjectMocks lateinit var viewModel: StatisticsViewModel

    @Test
    @DisplayName("Ensure we have the correct initial list values")
    fun correctInitialValues() {
        assertTrue(viewModel.statisticsItemsData.value == DEFAULT_LIST)
    }
}
