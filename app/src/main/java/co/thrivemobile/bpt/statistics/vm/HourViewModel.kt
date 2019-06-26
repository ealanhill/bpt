package co.thrivemobile.bpt.statistics.vm

import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.R
import co.thrivemobile.bpt.statistics.items.HourItem
import co.thrivemobile.bpt.util.NonNullLiveData

class HourViewModel : ViewModel() {

    companion object {
        private const val HIGH_COLOR = R.color.colorPrimary
        private const val LOW_COLOR = R.color.midwinter_fire
    }

    val timeLiveData = NonNullLiveData("0:00")
    val energyLiveData = NonNullLiveData("0")
    val energyTextColorData = NonNullLiveData(R.color.grey)
    val focusLiveData = NonNullLiveData("0")
    val focusTextColorData = NonNullLiveData(R.color.grey)
    val motivationLiveData = NonNullLiveData("0")
    val motivationTextColorData = NonNullLiveData(R.color.grey)

    fun setHourItem(item: HourItem) {
        timeLiveData.value = item.hour
        energyLiveData.value = item.energy.toString()
        focusLiveData.value = item.focus.toString()
        motivationLiveData.value = item.motivation.toString()

        energyTextColorData.value = getTextColor(item.energy)
        focusTextColorData.value = getTextColor(item.focus)
        motivationTextColorData.value = getTextColor(item.motivation)
    }

    private fun getTextColor(itemValue: Int) = if (itemValue > 5) {
        HIGH_COLOR
    } else {
        LOW_COLOR
    }
}
