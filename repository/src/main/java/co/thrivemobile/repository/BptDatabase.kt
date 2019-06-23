package co.thrivemobile.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Day::class, Entry::class], version = 1)
@TypeConverters(BptTypeConverters::class)
abstract class BptDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "BptDatabase.db"
        private var INSTANCE: BptDatabase? = null

        fun getInstance(context: Context): BptDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    BptDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE!!
        }
    }

    abstract fun bptDao(): BptDao
}
