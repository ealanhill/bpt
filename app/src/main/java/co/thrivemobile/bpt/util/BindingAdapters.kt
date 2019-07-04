package co.thrivemobile.bpt.util

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseBindingMethods
import com.google.android.material.chip.ChipGroup

@InverseBindingMethods(
    InverseBindingMethod(
       type = ChipGroup::class,
       attribute = "checked",
       method = "getCheckedChipId"
   )
)
object ChipGroupBindingAdapter {

    @InverseBindingAdapter(attribute = "checked")
    fun getChecked(chipGroup: ChipGroup): Int {
        return chipGroup.checkedChipId
    }

    @BindingAdapter("checked")
    fun setChecked(chipGroup: ChipGroup, id: Int) {
        chipGroup.check(id)
    }
}
