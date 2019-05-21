package co.thrivemobile.bpt.database

import androidx.room.TypeConverter
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.format.DateTimeFormatter

object BptTypeConverters {
    private val dateFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    private val timeFormatter = DateTimeFormatter.ISO_OFFSET_TIME

    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value: String?): OffsetDateTime? {
        return value?.let {
            dateFormatter.parse(value, OffsetDateTime::from)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime(date: OffsetDateTime?): String? {
        return date?.format(dateFormatter)
    }

    @TypeConverter
    @JvmStatic
    fun toOffsetTime(value: String?): OffsetTime? {
        return value?.let {
            timeFormatter.parse(value, OffsetTime::from)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromOffsetTime(time: OffsetTime?): String? {
        return time?.format(timeFormatter)
    }
}
