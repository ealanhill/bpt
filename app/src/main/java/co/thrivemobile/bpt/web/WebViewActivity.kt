package co.thrivemobile.bpt.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import co.thrivemobile.bpt.databinding.ActivityWebViewBinding
import co.thrivemobile.bpt.util.show
import org.koin.android.ext.android.inject

class WebViewActivity : AppCompatActivity() {

    companion object {
        private const val URL_TO_LOAD = "URL_TO_LOAD"

        fun start(context: Context, urlToLoad: String) {
            val intent = Intent(context, WebViewActivity::class.java).apply {
                putExtra(URL_TO_LOAD, urlToLoad)
            }

            context.startActivity(intent)
        }
    }

    private val webViewModel: WebViewModel by inject()
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = LayoutInflater.from(this)
        binding = ActivityWebViewBinding.inflate(inflater)
        setContentView(binding.root)

        webViewModel.webViewState.observe(this) { handleWebViewState(it) }
    }

    private fun handleWebViewState(webViewState: WebViewState) {
        binding.progressBar.show(webViewState.isLoading)
        binding.errorText.show(webViewState.hasError)

        if (webViewState.showContent) {
            binding.webView.show()

            binding.webView.loadData(webViewState.content, "text/html", "base64")
        }
    }
}
