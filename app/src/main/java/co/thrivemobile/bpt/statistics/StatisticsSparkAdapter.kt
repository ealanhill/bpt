package co.thrivemobile.bpt.statistics

import co.thrivemobile.repository.Entry
import com.robinhood.spark.SparkAdapter

class StatisticsSparkAdapter : SparkAdapter() {

    var dayEntries: MutableList<Entry> = mutableListOf()
        set(value) {
            field.apply {
                clear()
                addAll(value)
            }
            notifyDataSetChanged()
        }

    override fun getY(index: Int): Float = dayEntries[index].energy.toFloat()

    override fun getX(index: Int): Float {
        return dayEntries[index].id.toFloat()
    }

    override fun getItem(index: Int): Any = dayEntries[index]

    override fun getCount(): Int {
        return dayEntries.size
    }

}