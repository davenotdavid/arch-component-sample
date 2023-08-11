package com.davenotdavid.archcomponentsample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.navArgument
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.davenotdavid.archcomponentsample.ui.articles.ArticleDetailsScreen
//import com.davenotdavid.archcomponentsample.databinding.ActivityMainBinding
import com.davenotdavid.archcomponentsample.ui.articles.ArticlesScreen
import com.davenotdavid.archcomponentsample.ui.articles.ArticlesViewModel
import com.davenotdavid.archcomponentsample.ui.compose.theme.ComposeAppTheme
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * Marks an Android component class to be setup for injection with the standard
 * Hilt Dagger Android components.
 *
 * TODO: Decide what to keep when refactoring to Compose
 *  Ref doc: https://developer.android.com/jetpack/compose/navigation#interoperability
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // TODO: Remove
//    private lateinit var navController: NavController
//    private lateinit var appBarConfiguration: AppBarConfiguration
//    private lateinit var binding: ActivityMainBinding

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



        // TODO: Remove all
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val navView: BottomNavigationView = binding.navView
//
//        navController = findNavController(R.id.nav_host_fragment)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_articles, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
}