package com.davenotdavid.archcomponentsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.davenotdavid.archcomponentsample.ui.articledetail.ArticleDetailsScreen
import com.davenotdavid.archcomponentsample.ui.articles.ArticlesScreen
import com.davenotdavid.archcomponentsample.ui.articles.ArticlesViewModel
import com.davenotdavid.archcomponentsample.ui.compose.theme.ComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * Full Compose Navigation implementation to handle routing between screens,
 * passing data, and etc.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val articlesViewModel by viewModels<ArticlesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(text = stringResource(id = R.string.title_articles))
                    })
                }
                // TODO: FAB
            ) { padding ->
                ComposeAppTheme {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "articles",
                        // TODO: Padding official here?
                        modifier = Modifier.padding(padding)
                    ) {
                        composable(route = "articles") {
                            val state = articlesViewModel.uiState.collectAsState()
                            ArticlesScreen(
                                headlineState = state.value.headlineState,
                                onArticleClick = { article ->
                                    // Nav routes are equivalent to URLs (e.g. IDs generally work
                                    // as args) so need to encode here
                                    val encodedUrl = URLEncoder.encode(
                                        article.url,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navController.navigate("articleDetails/${encodedUrl}")
                                }
                            )
                        }

                        // TODO: Nav transitions
                        composable(
                            route = "articleDetails/{articleUrl}",
                            arguments = listOf(navArgument("articleUrl") { type = NavType.StringType })
                        ) { backStackEntry ->
                            // URL argument gets decoded
                            val articleUrl = backStackEntry.arguments?.getString("articleUrl")
                                ?: throw IllegalArgumentException("Article URL must not be null")
                            ArticleDetailsScreen(
                                navController = navController,
                                url = articleUrl
                            )
                        }
                    }
                }
            }
        }
    }

}
