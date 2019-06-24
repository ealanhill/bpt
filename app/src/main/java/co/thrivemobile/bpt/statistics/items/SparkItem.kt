package co.thrivemobile.bpt.statistics.items

import co.thrivemobile.repository.Entry
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.ZoneOffset

data class SparkItem(
    val items: List<Entry> = listOf(
        Entry(
            id = 0,
            date = OffsetDateTime.of(2019, 5, 21, 0, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 2
        ),
        Entry(
            id = 1,
            date = OffsetDateTime.of(2019, 5, 21, 1, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 5
        ),
        Entry(
            id = 2,
            date = OffsetDateTime.of(2019, 5, 21, 2, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 4
        ),
        Entry(
            id = 3,
            date = OffsetDateTime.of(2019, 5, 21, 3, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 8
        ),
        Entry(
            id = 4,
            date = OffsetDateTime.of(2019, 5, 21, 4, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 6
        ),
        Entry(
            id = 5,
            date = OffsetDateTime.of(2019, 5, 21, 5, 0, 0, 0, ZoneOffset.of("+02:00")),
            time = OffsetTime.of(19, 30, 0, 0, ZoneOffset.UTC),
            energy = 3
        )
    )
) : StatisticsItem
