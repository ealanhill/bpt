package co.thrivemobile.networking

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

class Network internal constructor(private var client: OkHttpClient) {

    companion object {
        lateinit var INSTANCE: Network
            private set
    }

    init {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    fun getMetaData(url: String, updateResults: (MetaData) -> Unit) {
        GlobalScope.launch {
            val result = requestUrl(url)
            Log.v("Network", "Got: $result")
            updateResults(MetaData())
        }
    }

    @WorkerThread
    private suspend fun requestUrl(url: String): String {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request)
            .execute()
            .use { response ->
                return@requestUrl response.body.toString()
            }
    }
}
