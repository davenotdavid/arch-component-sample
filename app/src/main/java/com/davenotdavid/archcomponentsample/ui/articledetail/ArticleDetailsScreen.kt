package com.davenotdavid.archcomponentsample.ui.articledetail

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

/**
 * TODO: Loading UI logic with [WebViewClient]?
 */
@Composable
@SuppressLint("SetJavaScriptEnabled")
fun ArticleDetailsScreen(
    url: String
) {
    AndroidView(factory = {
        WebView(it).apply {
            settings.javaScriptEnabled = true

            loadUrl(url)
        }
    })
}
