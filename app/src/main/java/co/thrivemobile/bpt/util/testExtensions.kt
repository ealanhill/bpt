package co.thrivemobile.bpt.util

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show(visible: Boolean) =
    if (visible) {
        show()
    } else {
        hide()
    }
