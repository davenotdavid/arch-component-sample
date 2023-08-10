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
        // TODO
        return ComposeView(requireContext()).apply {
//            setContent {
//                ComposeAppTheme {
//                    // TODO: Separate function?
//                    // TODO: How about side-effects?
//                    val state = viewModel.uiState.collectAsState()
//                    ArticlesScreen(headlineState = state.value.headlineState)
//                }
//            }
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
     * TODO: Remove in favor of Compose nav
     * TODO: Temporarily invoking ViewModel's setter functions until data/view
     *  binding is removed
     */
//    private fun observeStateEffectChanges() {
//        lifecycleScope.launchWhenStarted {
//            viewModel.uiState.collect {
//                when (it.headlineState) {
//                    is MviContract.HeadlineState.Idle -> {
//                        viewModel.setLoading(false)
//                    }
//                    is MviContract.HeadlineState.Loading -> {
//                        viewModel.setLoading(true)
//                    }
//                    is Success -> {
//                        viewModel.setLoading(false)
//                        viewModel.setArticles(it.headlineState.headline.articles)
//                    }
//                }
//            }
//        }
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.effect.collect {
//                when (it) {
//                    is MviContract.Effect.ShowToast -> {
//                        viewModel.setArticles(emptyList())
//                        Toast.makeText(this@ArticlesFragment.context, "An error has occurred", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//    }

    /**
     * TODO: Alternative to using Nav component not needing an XML layout? Or, hybrid approach for now?
     */
//    private fun goToArticleWebScreen(url: String) {
//        val action = ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailFragment(url)
//        findNavController().navigate(action)
//    }
}
