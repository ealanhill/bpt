package co.thrivemobile.bpt.statistics

import com.robinhood.spark.SparkAdapter

class StatisticsSparkAdapter : SparkAdapter() {

    var averages: MutableList<Float> = mutableListOf()
        set(value) {
            field.apply {
                clear()
                addAll(value)
            }
            notifyDataSetChanged()
        }

    override fun getY(index: Int): Float = averages[index]

    override fun getItem(index: Int): Any = averages[index]

    override fun getCount(): Int {
        return averages.size
    }
}
