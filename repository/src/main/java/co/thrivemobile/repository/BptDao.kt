package co.thrivemobile.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BptDao {

    @Query("SELECT * FROM ENTRIES ORDER BY id")
    fun getAllEntries(): List<Entry>

    @Query("SELECT * FROM DAYS ORDER BY id")
    fun getAllDays(): List<Day>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntry(entry: Entry)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDay(day: Day)
}
