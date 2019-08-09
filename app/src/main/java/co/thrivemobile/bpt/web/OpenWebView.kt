package co.thrivemobile.bpt.web

import android.content.Context
import android.content.Intent
import android.net.Uri

interface OpenWebView {
    fun openWebView(context: Context, url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
}
