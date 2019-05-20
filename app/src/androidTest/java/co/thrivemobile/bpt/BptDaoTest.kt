package co.thrivemobile.bpt

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.thrivemobile.bpt.database.BptDatabase
import co.thrivemobile.bpt.database.Entry
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BptDaoTest {

    @get:Rule val mActivityRule = ActivityTestRule(MainActivity::class.java)

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
        val entry = Entry(1, null, null)
        bptDatabase.bptDao().insertEntry(entry)

        val retrievedEntries = bptDatabase.bptDao().getAllEntries()
        assertTrue(retrievedEntries.isNotEmpty())
        assertTrue(retrievedEntries.first() == entry)
    }
}
