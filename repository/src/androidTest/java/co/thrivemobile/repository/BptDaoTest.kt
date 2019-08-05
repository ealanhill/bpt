package co.thrivemobile.repository

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import co.thrivemobile.repository.entities.Day
import co.thrivemobile.repository.entities.Entry
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.ZoneOffset

@RunWith(AndroidJUnit4::class)
class BptDaoTest {

    private lateinit var bptDatabase: BptDatabase

    @Before
    fun before() {
        bptDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            BptDatabase::class.java
        ).build()
    }

    @After
    fun after() {
        bptDatabase.close()
    }

    @Test
    fun when_we_insert_an_entry_it_is_actually_inserted() {
        val entry = Entry(
            id = 1,
            date = OffsetDateTime.of(2019, 5, 21, 0, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC)
        )
        bptDatabase.bptDao().insertEntry(entry)

        val retrievedEntries = bptDatabase.bptDao().getAllEntries()
        assertTrue(retrievedEntries.isNotEmpty())
        assertTrue(retrievedEntries.first() == entry)
    }

    @Test
    fun when_same_entry_is_inserted_then_new_one_replaces_old() {
        var entry = Entry(
            id = 1,
            date = null,
            time = null
        )
        bptDatabase.bptDao().insertEntry(entry)
        entry = Entry(
            id = 1,
            date = OffsetDateTime.of(2019, 5, 21, 0, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC)
        )
        bptDatabase.bptDao().insertEntry(entry)

        val retrievedEntry = bptDatabase.bptDao()
                .getAllEntries()
                .first()
        assertTrue(retrievedEntry == entry)
    }

    @Test
    fun when_a_second_entry_is_reinserted_then_new_one_replaces_the_old() {
        val entry1 = Entry(id = 1, date = null, time = null)
        var entry2 = Entry(id = 2, date = null, time = null)
        bptDatabase.bptDao().apply {
            insertEntry(entry1)
            insertEntry(entry2)
        }
        entry2 = Entry(
            id = 2,
            date = OffsetDateTime.of(2019, 5, 21, 0, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC)
        )
        bptDatabase.bptDao().insertEntry(entry2)

        val databaseEntries = bptDatabase.bptDao().getAllEntries()
        assertTrue(databaseEntries.size == 2)
        assertTrue(databaseEntries[1] == entry2)
    }

    @Test
    fun when_inserting_day_it_is_properly_inserted() {
        val day = Day(id = 1, day = null)
        bptDatabase.bptDao().insertDay(day)
        val retrievedDay = bptDatabase.bptDao().getAllDays().first()

        assertTrue(retrievedDay == day)
    }

    @Test
    fun when_same_day_is_inserted_then_new_one_is_ignored() {
        var day = Day(id = 1, day = null)
        bptDatabase.bptDao().insertDay(day)
        day = Day(id = 1, day = OffsetDateTime.of(2019, 5, 21, 0, 0, 0, 0, ZoneOffset.of("+02:00")))
        bptDatabase.bptDao().insertDay(day)
        val retrievedDay = bptDatabase.bptDao().getAllDays().first()

        assertFalse(retrievedDay == day)
    }

    @Test
    fun when_a_second_day_is_reinserted_then_new_one_is_ignored() {
        val day1 = Day(id = 1, day = null)
        var day2 = Day(id = 2, day = null)
        bptDatabase.bptDao().apply {
            insertDay(day1)
            insertDay(day2)
        }
        day2 = Day(id = 2, day = OffsetDateTime.of(2019, 5, 21, 0, 0, 0, 0, ZoneOffset.of("+02:00")))
        bptDatabase.bptDao().insertDay(day2)
        val retrievedDay = bptDatabase.bptDao().getAllDays()[1]

        assertFalse(retrievedDay == day2)
    }

    @Test
    fun when_retrieving_all_entries_all_entries_are_returned() {
        val entries = listOf(
            Entry(
                id = 1,
                date = OffsetDateTime.of(2019, 5, 21, 0, 0, 0, 0, ZoneOffset.of("+02:00")),
                time = null
            ),
            Entry(
                id = 2,
                date = OffsetDateTime.of(2019, 5, 22, 0, 0, 0, 0, ZoneOffset.of("+02:00")),
                time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC)
            ),
            Entry(
                id = 3,
                date = OffsetDateTime.of(2019, 5, 23, 0, 0, 0, 0, ZoneOffset.of("+02:00")),
                time = null
            ),
            Entry(
                id = 4,
                date = OffsetDateTime.of(2019, 5, 24, 0, 0, 0, 0, ZoneOffset.of("+02:00")),
                time = null
            )
        )
        entries.forEach { bptDatabase.bptDao().insertEntry(it) }

        val retrievedEntries = bptDatabase.bptDao().getAllEntries()
        assertTrue(retrievedEntries == entries)
    }

    @Test
    fun when_retrieving_all_days_all_entries_are_returned() {
        val days = listOf(
            Day(
                id = 1,
                day = OffsetDateTime.of(2019, 5, 21, 0, 0, 0, 0, ZoneOffset.of("+02:00"))
            ),
            Day(
                id = 2,
                day = OffsetDateTime.of(2019, 5, 22, 0, 0, 0, 0, ZoneOffset.of("+02:00"))
            ),
            Day(
                id = 3,
                day = OffsetDateTime.of(2019, 5, 23, 0, 0, 0, 0, ZoneOffset.of("+02:00"))
            ),
            Day(
                id = 4,
                day = OffsetDateTime.of(2019, 5, 24, 0, 0, 0, 0, ZoneOffset.of("+02:00"))
            )
        )
        days.forEach { bptDatabase.bptDao().insertDay(it) }

        val retrievedEntries = bptDatabase.bptDao().getAllDays()
        assertTrue(retrievedEntries == days)
    }
}
