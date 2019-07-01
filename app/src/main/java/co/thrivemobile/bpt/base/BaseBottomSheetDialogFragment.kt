package co.thrivemobile.bpt.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import co.thrivemobile.bpt.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomDialogFragment : BottomSheetDialogFragment() {

    fun showSoftKeyboard(view: View) {
        activity?.let {
            val inputMethodManager =
                it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun showExpanded(dialog: Dialog) {
        dialog.setOnShowListener { bottomSheetDialog ->
            val bottomSheetInternal =
                (bottomSheetDialog as BottomSheetDialog).findViewById<View>(R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheetInternal).state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}
