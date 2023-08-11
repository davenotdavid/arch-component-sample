package com.davenotdavid.archcomponentsample.ui.articles

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.davenotdavid.archcomponentsample.R

/**
 * TODO: Loading UI logic with [WebViewClient]?
 */
@Composable
@SuppressLint("SetJavaScriptEnabled, UnusedMaterialScaffoldPaddingParameter")
fun ArticleDetailsScreen(
    navController: NavHostController,
    url: String
) {
    // TODO: Nest `Scaffold`s per screen? Or rely on scaffold state of some sort?
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.title_article_details))
                },
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    null
                }
            )
        }
    ) { _ ->
        AndroidView(factory = {
            WebView(it).apply {
                settings.javaScriptEnabled = true

                loadUrl(url)
            }
        })
    }
}
