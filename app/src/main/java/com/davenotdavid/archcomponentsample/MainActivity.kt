package com.davenotdavid.archcomponentsample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
//import com.davenotdavid.archcomponentsample.databinding.ActivityMainBinding
import com.davenotdavid.archcomponentsample.ui.articles.ArticlesScreen
import com.davenotdavid.archcomponentsample.ui.articles.ArticlesViewModel
import com.davenotdavid.archcomponentsample.ui.compose.theme.ComposeAppTheme
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

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
            // TODO: Theming for toolbar and etc.
            ComposeAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "articles",
                ) {
                    composable(route = "articles") {
                        val state = articlesViewModel.uiState.collectAsState()
                        ArticlesScreen(headlineState = state.value.headlineState)
                    }

                    // TODO: Article details
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