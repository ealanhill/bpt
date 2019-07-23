package co.thrivemobile.bpt.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.chip.ChipGroup
import com.squareup.picasso.Picasso

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

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView?, url: String?) {
    if (url == null || url.isEmpty()) {
        return
    }

    Picasso.get()
        .load(url)
        .into(view)
}