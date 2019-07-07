package co.thrivemobile.bpt.entry_form

import co.thrivemobile.bpt.util.InstantExecutorExtension
import co.thrivemobile.bpt.util.observeOnce
import co.thrivemobile.repository.BptDao
import co.thrivemobile.repository.Entry
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.ZoneOffset

@ExtendWith(value = [ InstantExecutorExtension::class, MockitoExtension::class ])
class EntryFormViewModelTest {

    @Mock lateinit var mockRepo: BptDao
    private var now = { LocalDateTime.of(2019, 5, 1, 13, 0) }
    private lateinit var viewModel: EntryFormViewModel

    @BeforeEach
    fun beforeEach() {
        viewModel = EntryFormViewModel(mockRepo, now)
    }

    @Test
    @DisplayName("Ensure starting values are correct")
    fun verifyDefaultValues() {
        assertTrue(viewModel.timeLiveData.value == "1:00 PM")
        assertNull(viewModel.energyLiveData.value)
        assertNull(viewModel.focusLiveData.value)
        assertNull(viewModel.motivationLiveData.value)
        assertNull(viewModel.notesLiveData.value)

        viewModel.errorLiveData.observeOnce {
            assertNull(it)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user selects `cancel` ensure the event is fired")
    fun cancelSelected() {
        viewModel.onCancelSelected()

        assertTrue(viewModel.cancelLiveEvent.value!!)
    }

    @Test
    @DisplayName("When user enters a valid value for energy don't show error")
    fun validEnergyValue() {
        viewModel.energyLiveData.value = 5

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertFalse(it.energyError)
            assertTrue(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters 0 for energy then show the error")
    fun invalidEnergyValueZero() {
        viewModel.energyLiveData.value = 0

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.energyError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters a value greater than 10 for energy then show the error")
    fun invalidEnergyValueTen() {
        viewModel.energyLiveData.value = 15

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.energyError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters a null value for energy then show the error")
    fun energyNullValue() {
        viewModel.energyLiveData.value = null

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.energyError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When a user enters a negative value for energy then show error")
    fun energyNegativeValue() {
        viewModel.energyLiveData.value = -1

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.energyError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters an energy value at the edge of the value range don't show error")
    fun energyEdgeCase() {
        viewModel.energyLiveData.value = 1
        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertFalse(it.energyError)
            assertTrue(it.hasNoErrors)
        }
        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }

        viewModel.energyLiveData.value = 10
        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertFalse(it.energyError)
            assertTrue(it.hasNoErrors)
        }
        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    // *********

    @Test
    @DisplayName("When user enters a valid value for focus don't show error")
    fun validFocusValue() {
        viewModel.focusLiveData.value = 5

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertFalse(it.focusError)
            assertTrue(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters 0 for focus then show the error")
    fun invalidFocusValueZero() {
        viewModel.focusLiveData.value = 0

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.focusError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters a value greater than 10 for focus then show the error")
    fun invalidFocusValueTen() {
        viewModel.focusLiveData.value = 15

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.focusError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters a null value for focus then show the error")
    fun focusBlankString() {
        viewModel.focusLiveData.value = null

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.focusError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When a user enters a negative value for focus then show error")
    fun focusNegativeValue() {
        viewModel.focusLiveData.value = -1

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.focusError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters a focus value at the edge of the value range don't show error")
    fun focusEdgeCase() {
        viewModel.focusLiveData.value = 1
        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertFalse(it.focusError)
            assertTrue(it.hasNoErrors)
        }
        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }

        viewModel.focusLiveData.value = 10
        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertFalse(it.focusError)
            assertTrue(it.hasNoErrors)
        }
        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    // *********

    @Test
    @DisplayName("When user enters a valid value for motivation don't show error")
    fun validMotivationValue() {
        viewModel.motivationLiveData.value = 5

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertFalse(it.motivationError)
            assertTrue(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters 0 for motivation then show the error")
    fun invalidMotivationValueZero() {
        viewModel.motivationLiveData.value = 0

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.motivationError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters a value greater than 10 for motivation then show the error")
    fun invalidMotivationValueTen() {
        viewModel.motivationLiveData.value = 15

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.motivationError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters a null value for motivation then show the error")
    fun motivationBlankString() {
        viewModel.motivationLiveData.value = null

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.motivationError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When a user enters a negative value for motivation then show error")
    fun motivationNegativeValue() {
        viewModel.motivationLiveData.value = -1

        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertTrue(it.motivationError)
            assertFalse(it.hasNoErrors)
        }

        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user enters a motivation value at the edge of the value range don't show error")
    fun motivationEdgeCase() {
        viewModel.motivationLiveData.value = 1
        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertFalse(it.motivationError)
            assertTrue(it.hasNoErrors)
        }
        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }

        viewModel.motivationLiveData.value = 10
        viewModel.errorLiveData.observeOnce {
            assertNotNull(it)
            assertFalse(it.motivationError)
            assertTrue(it.hasNoErrors)
        }
        viewModel.enableSubmit.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When user presses submit, ensure the entry is properly saved to the DB")
    fun saveData() {
        val testValue = 5
        val testNote = "a simple note"

        viewModel.apply {
            energyLiveData.value = testValue
            focusLiveData.value = testValue
            motivationLiveData.value = testValue
            notesLiveData.value = testNote
        }

        viewModel.onSaveSelected()

        val testEntry = Entry(
            date = OffsetDateTime.of(2019, 5, 1, 13, 0, 0, 0, ZoneOffset.UTC),
            time = OffsetTime.of(13, 0, 0, 0, ZoneOffset.UTC),
            energy = testValue,
            focus = testValue,
            motivation = testValue,
            notes = testNote
        )

        verify(mockRepo).insertEntry(testEntry)

        assertTrue(viewModel.saveLivedata.value!!)
    }
}
