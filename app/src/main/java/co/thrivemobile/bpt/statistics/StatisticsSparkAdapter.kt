package co.thrivemobile.bpt.statistics

import android.graphics.RectF
import com.robinhood.spark.SparkAdapter

class StatisticsSparkAdapter : SparkAdapter() {

    var averages: MutableList<SparkPoint> = mutableListOf()
        set(value) {
            field.apply {
                clear()
                addAll(value)
            }
            notifyDataSetChanged()
        }

    override fun getY(index: Int): Float = averages[index].y

    override fun getX(index: Int): Float = averages[index].x

    override fun getItem(index: Int): Any = averages[index]

    override fun getCount(): Int {
        return averages.size
    }

    override fun getDataBounds(): RectF = RectF(0f, 0f, 24f, 10f)
}
