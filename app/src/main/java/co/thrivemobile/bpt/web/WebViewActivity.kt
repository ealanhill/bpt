package co.thrivemobile.bpt.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import co.thrivemobile.bpt.databinding.ActivityWebViewBinding

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

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = LayoutInflater.from(this)
        binding = ActivityWebViewBinding.inflate(inflater)
        setContentView(binding.root)

        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()

            val urlToLoad = intent.getStringExtra(URL_TO_LOAD)
            loadUrl(urlToLoad)
        }
    }
}
