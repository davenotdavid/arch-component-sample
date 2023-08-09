package com.davenotdavid.archcomponentsample.ui.articles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.compose.AsyncImage
//import com.bumptech.glide.Glide
//import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
//import com.bumptech.glide.integration.compose.GlideImage
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.model.Headline
import com.davenotdavid.archcomponentsample.model.MviContract
import com.davenotdavid.archcomponentsample.ui.compose.theme.ComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import com.davenotdavid.archcomponentsample.model.MviContract.HeadlineState.*
import com.davenotdavid.archcomponentsample.model.Source
import com.davenotdavid.archcomponentsample.ui.compose.theme.Purple200
import com.davenotdavid.archcomponentsample.R
import com.davenotdavid.archcomponentsample.ui.compose.theme.Teal200

/**
 * TODO: Finalize
 * TODO: Compose theme
 */
@AndroidEntryPoint
class ArticlesFragment : Fragment() {

    private val viewModel by viewModels<ArticlesViewModel>()
    // TODO
//    private lateinit var binding: FragmentArticlesBinding

    // TODO
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeAppTheme {
                    // TODO: Separate function?
                    // TODO: How about side-effects?
                    val state = viewModel.uiState.collectAsState()
                    ArticlesScreen(headlineState = state.value.headlineState)
                }
            }
        }


        // TODO
//        binding = FragmentArticlesBinding.inflate(inflater, container, false).apply {
//            viewmodel = articlesViewModel
//        }
//        return homeDataBinding.root
    }

    // TODO
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Sets the lifecycle owner to observe LiveData changes in this binding that
//        // then updates the UI.
//        homeDataBinding.lifecycleOwner = this.viewLifecycleOwner
//
//        initArticleAdapter()
//        initRefreshFab()
//        initNavigation()
//        observeStateEffectChanges()
//    }

    // TODO
//    private fun initArticleAdapter() {
//        val viewModel = homeDataBinding.viewmodel
//        if (viewModel != null) {
//            val adapter = ArticlesAdapter(viewModel)
//            homeDataBinding.headlineArticleList.adapter = adapter
//        } else {
//            Log.w("tag", "Failed to set up article adapter with ViewModel")
//        }
//    }

    // TODO
//    private fun initRefreshFab() {
//        homeDataBinding.refreshHeadlineButton.setOnClickListener {
//            articlesViewModel.setEvent(MviContract.Event.OnRefreshHeadlineClicked)
//        }
//    }

    // TODO
//    private fun initNavigation() {
//        articlesViewModel.openArticleWebEvent.observe(viewLifecycleOwner, EventObserver { url ->
//            goToArticleWebScreen(url)
//        })
//    }

    /**
     * To replicate the behavior of a `LiveData`, `launchWhenStarted()` is used so
     * that the Flow (UI State and Effect) will be collected when the lifecycle is
     * started at least.
     *
     * TODO: Temporarily invoking ViewModel's setter functions until data/view
     *  binding is removed
     */
    private fun observeStateEffectChanges() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it.headlineState) {
                    is MviContract.HeadlineState.Idle -> {
                        viewModel.setLoading(false)
                    }
                    is MviContract.HeadlineState.Loading -> {
                        viewModel.setLoading(true)
                    }
                    is Success -> {
                        viewModel.setLoading(false)
                        viewModel.setArticles(it.headlineState.headline.articles)
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                when (it) {
                    is MviContract.Effect.ShowToast -> {
                        viewModel.setArticles(emptyList())
                        Toast.makeText(this@ArticlesFragment.context, "An error has occurred", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    /**
     * TODO: Alternative to using Nav component not needing an XML layout? Or, hybrid approach for now?
     */
//    private fun goToArticleWebScreen(url: String) {
//        val action = ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailFragment(url)
//        findNavController().navigate(action)
//    }
}

// TODO: Placement

/**
 * TODO: Finalize modifiers
 * TODO: Coroutine scope placement?
 * TODO: Decouple Composable components
 * TODO: Other params like content padding?
 * TODO: On click listener
 * TODO: Lazy list state?
 * TODO: Try Live Edit with Studio version Giraffe
 */
// TODO: @OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticlesScreen(
    modifier: Modifier = Modifier,
    headlineState: MviContract.HeadlineState
) {
//    val state = viewModel.uiState.collectAsState()
    // TODO: How about for side-effects?

    when (headlineState) {
        is Loading -> {
            FullScreenLoading()
        }
        is Idle -> {
            // TODO
            Log.d("tag", "Idle")
        }
        is Success -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                val headline = headlineState.headline

                items(items = headline.articles, key = { it.id }) { article ->
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable {
                            // TODO
                        }
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            val placeholder = painterResource(id = R.drawable.ic_placeholder)
                            AsyncImage(
                                modifier = Modifier.weight(0.3f),
                                model = article.url,
                                contentDescription = "Article Image",
                                error = placeholder,
                                fallback = placeholder
                            )

                            // TODO: Testing purposes
//                            Image(
//                                modifier = Modifier.weight(0.3f),
//                                painter = placeholder,
//                                contentDescription = ""
//                            )

                            Column(modifier = Modifier.weight(0.7f)) {
                                Text(
                                    text = article.title,
                                    color = Purple200
                                )

                                Text(
                                    text = article.publishedAt
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Full screen circular progress indicator
 *
 * TODO: Official?
 */
@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

/**
 * TODO
 */
@Preview
@Composable
fun PreviewArticlesScreen() {
    ComposeAppTheme {
        val dummyHeadline = Headline(
            id = "1",
            status = "ok",
            totalResults = 7618,
            articles = listOf(
                Article(
                    id = "11",
                    source = Source(
                        id = "111",
                        name = "MakeUseOf"
                    ),
                    author = "Emma Garofalo",
                    title = "Tesla Updates Passenger Play to Stop Drivers Playing Games While Driving",
                    description = "Tesla is updating its Passenger Play feature to ensure drivers and passengers alike can only play games while the car is parked.",
                    url = "https://www.makeuseof.com/tesla-updates-passenger-play-feature/",
                    urlToImage = "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2021/12/tesla-passenger-play.jpg",
                    publishedAt = "2021-12-26T16:40:20Z",
                    content = "After a National Highway Traffic Safety Administration (NHTSA) review, Tesla is officially pulling the plug on gaming while driving.\r\nThe company's controversial Passenger Play feature is being diale… [+2486 chars]"
                ),
                Article(
                    id = "22",
                    source = Source(
                        id = "222",
                        name = "MakeUseOf"
                    ),
                    author = "Emma Garofalo",
                    title = "Tesla Updates Passenger Play to Stop Drivers Playing Games While Driving",
                    description = "Tesla is updating its Passenger Play feature to ensure drivers and passengers alike can only play games while the car is parked.",
                    url = "https://www.makeuseof.com/tesla-updates-passenger-play-feature/",
                    urlToImage = "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2021/12/tesla-passenger-play.jpg",
                    publishedAt = "2021-12-26T16:40:20Z",
                    content = "After a National Highway Traffic Safety Administration (NHTSA) review, Tesla is officially pulling the plug on gaming while driving.\r\nThe company's controversial Passenger Play feature is being diale… [+2486 chars]"
                ),
                Article(
                    id = "33",
                    source = Source(
                        id = "333",
                        name = "MakeUseOf"
                    ),
                    author = "Emma Garofalo",
                    title = "Tesla Updates Passenger Play to Stop Drivers Playing Games While Driving",
                    description = "Tesla is updating its Passenger Play feature to ensure drivers and passengers alike can only play games while the car is parked.",
                    url = "https://www.makeuseof.com/tesla-updates-passenger-play-feature/",
                    urlToImage = "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2021/12/tesla-passenger-play.jpg",
                    publishedAt = "2021-12-26T16:40:20Z",
                    content = "After a National Highway Traffic Safety Administration (NHTSA) review, Tesla is officially pulling the plug on gaming while driving.\r\nThe company's controversial Passenger Play feature is being diale… [+2486 chars]"
                ),
                Article(
                    id = "44",
                    source = Source(
                        id = "444",
                        name = "MakeUseOf"
                    ),
                    author = "Emma Garofalo",
                    title = "Tesla Updates Passenger Play to Stop Drivers Playing Games While Driving",
                    description = "Tesla is updating its Passenger Play feature to ensure drivers and passengers alike can only play games while the car is parked.",
                    url = "https://www.makeuseof.com/tesla-updates-passenger-play-feature/",
                    urlToImage = "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2021/12/tesla-passenger-play.jpg",
                    publishedAt = "2021-12-26T16:40:20Z",
                    content = "After a National Highway Traffic Safety Administration (NHTSA) review, Tesla is officially pulling the plug on gaming while driving.\r\nThe company's controversial Passenger Play feature is being diale… [+2486 chars]"
                ),
                Article(
                    id = "55",
                    source = Source(
                        id = "555",
                        name = "MakeUseOf"
                    ),
                    author = "Emma Garofalo",
                    title = "Tesla Updates Passenger Play to Stop Drivers Playing Games While Driving",
                    description = "Tesla is updating its Passenger Play feature to ensure drivers and passengers alike can only play games while the car is parked.",
                    url = "https://www.makeuseof.com/tesla-updates-passenger-play-feature/",
                    urlToImage = "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2021/12/tesla-passenger-play.jpg",
                    publishedAt = "2021-12-26T16:40:20Z",
                    content = "After a National Highway Traffic Safety Administration (NHTSA) review, Tesla is officially pulling the plug on gaming while driving.\r\nThe company's controversial Passenger Play feature is being diale… [+2486 chars]"
                )
            )
        )

        ArticlesScreen(
            headlineState = Success(dummyHeadline)
        )
    }
}
