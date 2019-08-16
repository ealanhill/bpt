package co.thrivemobile.repository.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetTime
import org.threeten.bp.ZoneOffset

@Entity(tableName = "SETTINGS")
data class Settings(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "user_first_name") val userFirstName: String,
    @ColumnInfo(name = "user_last_name") val userLastName: String,
    @ColumnInfo(name = "user_email") val userEmail: String,
    @ColumnInfo(name = "start_tracking_time")
    val startTrackingTime: OffsetTime = OffsetTime.of(8, 0, 0, 0, ZoneOffset.UTC),
    @ColumnInfo(name = "end_tracking_time")
    val endTrackingTime: OffsetTime = OffsetTime.of(17, 0, 0, 0, ZoneOffset.UTC)
)
