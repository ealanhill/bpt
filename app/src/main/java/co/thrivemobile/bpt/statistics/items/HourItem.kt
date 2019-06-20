package co.thrivemobile.bpt.statistics.items

data class HourItem(
    val hour: String,
    val energy: Int,
    val focus: Int,
    val motivation: Int
) : StatisticsItem
