package co.thrivemobile.bpt.util

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.Group
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import co.thrivemobile.bpt.R
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
        .placeholder(R.drawable.ic_placeholder)
        .into(view)
}

@BindingAdapter("groupVisibility")
fun setGroupVisibility(group: Group?, visible: Boolean?) {
    if (group == null || visible == null) {
        return
    }

    group.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
