package co.thrivemobile.bpt.statistics.vm

import co.thrivemobile.bpt.statistics.StatisticsViewModel
import co.thrivemobile.bpt.statistics.items.EntryItem
import co.thrivemobile.bpt.statistics.items.HourItem
import co.thrivemobile.bpt.statistics.items.SparkItem
import co.thrivemobile.bpt.statistics.items.StatisticsItem
import co.thrivemobile.bpt.util.InstantExecutorExtension
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.bpt.util.observeOnce
import co.thrivemobile.repository.BptDao
import co.thrivemobile.repository.Entry
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.ZoneOffset

@ExtendWith(value = [ InstantExecutorExtension::class, MockitoExtension::class ])
class StatisticsViewModelTest {

    companion object {
        private val DEFAULT_LIST = listOf(
            SparkItem(),
            EntryItem()
        )

        private val ENTRIES = listOf(
            Entry(id = 0,
                  date = OffsetDateTime.of(2019, 5, 1, 13, 0, 0, 0, ZoneOffset.UTC),
                  time = OffsetTime.of(13, 0, 0, 0, ZoneOffset.UTC),
                  energy = 5,
                  focus = 5,
                  motivation = 5
            ),
            Entry(id = 1,
                  date = OffsetDateTime.of(2019, 5, 1, 14, 0, 0, 0, ZoneOffset.UTC),
                  time = OffsetTime.of(14, 0, 0, 0, ZoneOffset.UTC),
                  energy = 6,
                  focus = 7,
                  motivation = 8
            ),
            Entry(id = 2,
                  date = OffsetDateTime.of(2019, 5, 1, 15, 0, 0, 0, ZoneOffset.UTC),
                  time = OffsetTime.of(15, 0, 0, 0, ZoneOffset.UTC),
                  energy = 2,
                  focus = 2,
                  motivation = 2
            )
        )

        private val NEW_ENTRIES: List<Entry>
            get() {
                val newEntries = ENTRIES.toMutableList()
                newEntries.add(
                    Entry(id = 3,
                          date = OffsetDateTime.of(2019, 5, 1, 15, 0, 0, 0, ZoneOffset.UTC),
                          time = OffsetTime.of(16, 0, 0, 0, ZoneOffset.UTC),
                          energy = 4,
                          focus = 5,
                          motivation = 6
                    )
                )
                return newEntries
            }

        private val LIST_WITH_ENTRIES = listOf(
            SparkItem(),
            EntryItem(),
            HourItem(hour = "1:00 PM",
                     energy = 5,
                     focus = 5,
                     motivation = 5),
            HourItem(hour = "2:00 PM",
                     energy = 6,
                     focus = 7,
                     motivation = 8),
            HourItem(hour = "3:00 PM",
                     energy = 2,
                     focus = 2,
                     motivation = 2)
        )

        private val LIST_WITH_NEW_ENTRIES: List<StatisticsItem>
            get() {
                val newItems = LIST_WITH_ENTRIES.toMutableList()
                newItems.add(
                    HourItem(hour = "4:00 PM",
                             energy = 4,
                             focus = 5,
                             motivation = 6)
                )
                return newItems
            }
    }

    @Mock lateinit var mockRepo: BptDao
    private val dateTime = OffsetDateTime.of(2019, 5, 1, 13, 0, 0, 0, ZoneOffset.UTC)
    private var now = { dateTime }
    private lateinit var viewModel: StatisticsViewModel

    @Test
    @DisplayName("Ensure we have the correct initial list values")
    fun correctInitialValues() {
        val emptyLiveData = NonNullLiveData(emptyList<Entry>())
        `when`(mockRepo.getEntriesForDay(dateTime)).thenReturn(emptyLiveData)
        viewModel = StatisticsViewModel(mockRepo, now)

        viewModel.statisticsItemsLiveData.observeOnce {
            assertTrue(it == DEFAULT_LIST)
        }
    }

    @Test
    @DisplayName("When there are hour entries then those are appropriately added to the list")
    fun startWithHourItems() {
        val entriesLiveData = NonNullLiveData(ENTRIES)
        `when`(mockRepo.getEntriesForDay(dateTime)).thenReturn(entriesLiveData)
        viewModel = StatisticsViewModel(mockRepo, now)

        viewModel.statisticsItemsLiveData.observeOnce {
            assertTrue(it == LIST_WITH_ENTRIES)
        }
    }

    @Test
    @DisplayName("When a new hour entry is added to the database it is added to the list")
    fun addHourItems() {
        val entriesLiveData = NonNullLiveData(ENTRIES)
        `when`(mockRepo.getEntriesForDay(dateTime)).thenReturn(entriesLiveData)
        viewModel = StatisticsViewModel(mockRepo, now)

        entriesLiveData.postValue(NEW_ENTRIES)
        viewModel.statisticsItemsLiveData.observeOnce {
            assertTrue(it == LIST_WITH_NEW_ENTRIES)
        }
    }
}
