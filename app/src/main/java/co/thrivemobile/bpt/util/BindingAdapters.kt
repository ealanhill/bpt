package co.thrivemobile.bpt.util

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.chip.ChipGroup

@BindingAdapter(value = ["checked", "checkedAttrChanged"], requireAll = false)
fun setChecked(chipGroup: ChipGroup, id: Int, listener: InverseBindingListener) {
    chipGroup.check(id)
    chipGroup.setOnCheckedChangeListener { _, _ ->
        listener.onChange()
    }
}

@InverseBindingAdapter(attribute = "checked", event = "checkedAttrChanged")
fun getChecked(chipGroup: ChipGroup): Int {
    return chipGroup.checkedChipId
}