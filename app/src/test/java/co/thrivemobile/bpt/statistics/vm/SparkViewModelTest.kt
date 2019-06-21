package co.thrivemobile.bpt.statistics.vm

import co.thrivemobile.bpt.statistics.items.SparkItem
import co.thrivemobile.bpt.util.InstantExecutorExtension
import co.thrivemobile.repository.Entry
import junit.framework.Assert.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.ZoneOffset

@ExtendWith(InstantExecutorExtension::class)
class SparkViewModelTest {

    private lateinit var viewModel: SparkViewModel
    private val mockEntries = listOf(
        Entry(
            id = 0,
            date = OffsetDateTime.of(2019, 5, 21, 0, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 2
        ),
        Entry(
            id = 0,
            date = OffsetDateTime.of(2019, 5, 21, 1, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 5
        ),
        Entry(
            id = 0,
            date = OffsetDateTime.of(2019, 5, 21, 2, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 4
        ),
        Entry(
            id = 0,
            date = OffsetDateTime.of(2019, 5, 21, 3, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 8
        ),
        Entry(
            id = 0,
            date = OffsetDateTime.of(2019, 5, 21, 4, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 6
        ),
        Entry(
            id = 0,
            date = OffsetDateTime.of(2019, 5, 21, 5, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 3
        )
    )

    @BeforeEach
    fun before() {
        val item = SparkItem(mockEntries)
        viewModel = SparkViewModel(item)
    }

    @Nested
    @DisplayName("Initialization")
    inner class Initialization {

        @Test
        @DisplayName("when initializing view model ensure the correct data is given")
        fun ensureStartDataIsCorrect () {
            assertTrue(viewModel.dayEntriesLiveData.value == mockEntries)
        }
    }
}
