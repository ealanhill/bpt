package co.thrivemobile.bpt.web

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import co.thrivemobile.bpt.R

interface OpenWebView {
    fun openWebView(context: Context, url: String) {
        val customTabIntent = CustomTabsIntent.Builder()
            .setShowTitle(true)
            .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .addDefaultShareMenuItem()
            .build()

        val uri = Uri.parse(url)
        customTabIntent.launchUrl(context, uri)
    }
}
