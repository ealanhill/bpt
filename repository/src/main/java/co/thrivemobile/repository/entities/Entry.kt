package co.thrivemobile.repository.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime

@Entity(tableName = "ENTRIES")
data class Entry(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "date") val date: OffsetDateTime?,
    @ColumnInfo(name = "time") val time: OffsetTime?,
    @ColumnInfo(name = "energy") val energy: Int = 0,
    @ColumnInfo(name = "focus") val focus: Int = 0,
    @ColumnInfo(name = "motivation") val motivation: Int = 0,
    @ColumnInfo(name = "notes") val notes: String = ""
) {

    val average: Float
        get() {
            val total = energy + focus + motivation
            return total.toFloat() / 3f
        }
}
