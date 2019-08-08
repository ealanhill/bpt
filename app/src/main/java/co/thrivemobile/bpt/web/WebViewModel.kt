package co.thrivemobile.bpt.web

import androidx.lifecycle.ViewModel
import co.thrivemobile.bpt.util.NonNullLiveData
import co.thrivemobile.repository.BptDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class WebViewModel(ioDispatcher: CoroutineDispatcher, private val dao: BptDao) : ViewModel() {

    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(ioDispatcher + job)

    val webViewState = NonNullLiveData(WebViewState())

    fun loadWebsite(urlToLoad: String) {
        ioScope.launch {
            val databaseArticle = dao.getArticle(urlToLoad)

            val currentState = webViewState.value

            if (databaseArticle == null) {
                webViewState.value = currentState.copy(isLoading = false, hasError = true)
            } else {
                webViewState.value = currentState.copy(
                    isLoading = false,
                    content = databaseArticle.content,
                    hasError = false
                )
            }
        }
    }
}
