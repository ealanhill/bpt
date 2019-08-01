package co.thrivemobile.networking

import androidx.annotation.WorkerThread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.regex.Matcher

class Network(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher,
    private val client: OkHttpClient
) {

    private val job = SupervisorJob()
    private val mainScope = CoroutineScope(mainDispatcher + job)
    private val ioScope = CoroutineScope(ioDispatcher + job)

    fun getMetaData(url: String, updateResults: (MetaData) -> Unit) {
        mainScope.launch {
            val result = requestUrl(url)
            updateResults(extractMetaData(result))
        }
    }

    @WorkerThread
    private suspend fun requestUrl(url: String): String {
        val request = Request.Builder()
            .url(url)
            .build()

        return withContext(ioScope.coroutineContext) {
            client.newCall(request)
                .execute()
                .use { it.body?.string() ?: "" }
        }
    }

    private fun extractMetaData(resultHtml: String): MetaData {
        val title = getMetaTagContents(TwitterTags.TITLE.matcher(resultHtml))
        val description = getMetaTagContents(TwitterTags.DESCRIPTION.matcher(resultHtml))
        val imageUrl = getMetaTagContents(TwitterTags.IMAGE_URL.matcher(resultHtml))
        return MetaData(title, description, imageUrl)
    }

    private fun getMetaTagContents(matcher: Matcher): String {
        return if (matcher.find()) {
            matcher.group(1)
        } else {
            ""
        }
    }
}
