package co.thrivemobile.bpt.web

data class WebViewState(
    val isLoading: Boolean = true,
    val content: String = "",
    val hasError: Boolean = false
) {

    val showContent: Boolean
        get() = !(isLoading || hasError) && content.isNotEmpty()
}
