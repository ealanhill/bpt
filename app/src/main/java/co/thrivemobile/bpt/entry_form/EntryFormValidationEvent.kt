package co.thrivemobile.bpt.entry_form

data class EntryFormValidationEvent(
    var energyError: Boolean = false,
    var focusError: Boolean = false,
    var motivationError: Boolean = false
) {

    val hasNoErrors: Boolean
        get() {
            return !(energyError || focusError || motivationError)
        }
}
