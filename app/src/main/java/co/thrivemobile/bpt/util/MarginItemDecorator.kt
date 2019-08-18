package co.thrivemobile.bpt.util

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecorator(@DimenRes private val spacingRes: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val spacing = view.context.resources.getDimension(spacingRes).toInt()

        outRect.apply {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spacing
            }
            left = spacing
            right = spacing
            bottom = spacing
        }
    }
}
