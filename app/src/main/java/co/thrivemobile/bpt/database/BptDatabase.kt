package co.thrivemobile.bpt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Day::class, Entry::class], version = 1)
@TypeConverters(BptTypeConverters::class)
abstract class BptDatabase : RoomDatabase() {
    abstract fun bptDao(): BptDao
}
