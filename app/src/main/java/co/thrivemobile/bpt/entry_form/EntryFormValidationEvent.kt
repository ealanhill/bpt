package co.thrivemobile.bpt.entry_form

import androidx.annotation.StringRes

data class EntryFormValidationEvent(
    @StringRes var energyErrorRes: Int? = null,
    @StringRes var focusErrorRes: Int? = null,
    @StringRes var motivationErrorRes: Int? = null
) {

    val hasErrors: Boolean
        get() {
            return energyErrorRes == null && focusErrorRes == null && motivationErrorRes == null
        }
}
