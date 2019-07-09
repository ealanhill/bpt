package co.thrivemobile.repository

import androidx.room.TypeConverter
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import org.threeten.bp.temporal.ChronoField
import org.threeten.bp.temporal.TemporalField

object BptTypeConverters {

    private val dateFormatter = DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_OFFSET_DATE)
        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
        .toFormatter()
    private val timeFormatter = DateTimeFormatter.ISO_OFFSET_TIME

    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value: String?): OffsetDateTime? {
        return value?.let {
            OffsetDateTime.parse(it, dateFormatter)
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
