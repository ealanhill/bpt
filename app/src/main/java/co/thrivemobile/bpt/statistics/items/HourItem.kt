package co.thrivemobile.bpt.statistics.items

data class HourItem(
    val hour: String = "0:00",
    val energy: Int = 10,
    val focus: Int = 10,
    val motivation: Int = 10
) : StatisticsItem
