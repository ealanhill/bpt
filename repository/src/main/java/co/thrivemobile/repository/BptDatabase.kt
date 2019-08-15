package co.thrivemobile.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.thrivemobile.repository.entities.DatabaseArticle
import co.thrivemobile.repository.entities.Day
import co.thrivemobile.repository.entities.Entry
import co.thrivemobile.repository.entities.Settings

@Database(
    entities = [
        Day::class,
        Entry::class,
        DatabaseArticle::class,
        Settings::class
    ],
    version = 1
)
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
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }

    abstract fun bptDao(): BptDao
}
