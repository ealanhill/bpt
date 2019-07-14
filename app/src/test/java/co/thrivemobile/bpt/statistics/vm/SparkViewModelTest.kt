package co.thrivemobile.bpt.statistics.vm

import co.thrivemobile.bpt.statistics.SparkPoint
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
class SparkViewModelTest {

    companion object {
        private val allEntries = listOf(
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
            ),
            Entry(id = 3,
                  date = OffsetDateTime.of(2019, 5, 1, 16, 0, 0, 0, ZoneOffset.UTC),
                  time = OffsetTime.of(16, 0, 0, 0, ZoneOffset.UTC),
                  energy = 4,
                  focus = 5,
                  motivation = 6
            ),
            Entry(id = 4,
                  date = OffsetDateTime.of(2019, 5, 1, 17, 0, 0, 0, ZoneOffset.UTC),
                  time = OffsetTime.of(17, 0, 0, 0, ZoneOffset.UTC),
                  energy = 7,
                  focus = 6,
                  motivation = 5
            )
        )

        private val points = listOf(
            SparkPoint(13f, 5f),
            SparkPoint(14f, 7f),
            SparkPoint(15f, 2f),
            SparkPoint(16f, 5f),
            SparkPoint(17f, 6f)
        )
    }

    @Mock lateinit var mockRepo: BptDao
    private val dateTime = OffsetDateTime.of(2019, 5, 1, 13, 0, 0, 0, ZoneOffset.UTC)
    private var now = { dateTime }
    private lateinit var viewModel: SparkViewModel

    @Test
    @DisplayName("When loading, ensure we map the entries to the points correctly")
    fun loadEntries() {
        val entriesLiveData = NonNullLiveData(allEntries)
        `when`(mockRepo.getEntriesForDay(dateTime)).thenReturn(entriesLiveData)
        viewModel = SparkViewModel(mockRepo, now)
        viewModel.entriesMediated.observeOnce {
            assertTrue(it == points)
        }
    }

    @Test
    @DisplayName("When a new value is added to the database, then it is added to the list")
    fun updateList() {
        val entriesLiveData = NonNullLiveData(allEntries)
        `when`(mockRepo.getEntriesForDay(dateTime)).thenReturn(entriesLiveData)
        viewModel = SparkViewModel(mockRepo, now)

        val newEntry = Entry(id = 5,
                             date = OffsetDateTime.of(2019, 5, 1, 18, 0, 0, 0, ZoneOffset.UTC),
                             time = OffsetTime.of(18, 0, 0, 0, ZoneOffset.UTC),
                             energy = 4,
                             focus = 4,
                             motivation = 5
        )
        val newEntries = allEntries.toMutableList()
        newEntries.add(newEntry)
        entriesLiveData.postValue(newEntries)

        val newPoints = points.toMutableList()
        newPoints.add(SparkPoint(18f, 4.3333335f))

        viewModel.entriesMediated.observeOnce {
            assertTrue(it == newPoints)
        }
    }

    @Test
    @DisplayName("Duplicate values are not added to the list")
    fun duplicates() {
        val entriesLiveData = NonNullLiveData(allEntries)
        `when`(mockRepo.getEntriesForDay(dateTime)).thenReturn(entriesLiveData)
        viewModel = SparkViewModel(mockRepo, now)

        val newEntry = Entry(id = 4,
                             date = OffsetDateTime.of(2019, 5, 1, 17, 0, 0, 0, ZoneOffset.UTC),
                             time = OffsetTime.of(17, 0, 0, 0, ZoneOffset.UTC),
                             energy = 7,
                             focus = 6,
                             motivation = 5
        )
        val newEntries = allEntries.toMutableList()
        newEntries.add(newEntry)
        entriesLiveData.postValue(newEntries)

        viewModel.entriesMediated.observeOnce {
            assertTrue(it == points)
        }
    }

    @Test
    @DisplayName("Ensure date is properly formatted")
    fun formatDate() {
        viewModel = SparkViewModel(mockRepo, now)

        assertTrue(viewModel.dateLiveData.value == "May 1, 2019")
    }
}
