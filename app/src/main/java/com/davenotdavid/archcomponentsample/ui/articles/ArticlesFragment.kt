package com.davenotdavid.archcomponentsample.ui.articles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.davenotdavid.archcomponentsample.databinding.FragmentArticlesBinding
import com.davenotdavid.archcomponentsample.model.MviContract
import com.davenotdavid.archcomponentsample.ui.articles.adapter.ArticlesAdapter
import com.davenotdavid.archcomponentsample.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesFragment : Fragment() {

    private val articlesViewModel by viewModels<ArticlesViewModel>()
    private lateinit var homeDataBinding: FragmentArticlesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeDataBinding = FragmentArticlesBinding.inflate(inflater, container, false).apply {
            viewmodel = articlesViewModel
        }
        return homeDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sets the lifecycle owner to observe LiveData changes in this binding that
        // then updates the UI.
        homeDataBinding.lifecycleOwner = this.viewLifecycleOwner

        initArticleAdapter()
        initRefreshFab()
        initNavigation()
        observeStateEffectChanges()
    }

    private fun initArticleAdapter() {
        val viewModel = homeDataBinding.viewmodel
        if (viewModel != null) {
            val adapter = ArticlesAdapter(viewModel)
            homeDataBinding.headlineArticleList.adapter = adapter
        } else {
            Log.w("tag", "Failed to set up article adapter with ViewModel")
        }
    }

    private fun initRefreshFab() {
        homeDataBinding.refreshHeadlineButton.setOnClickListener {
            articlesViewModel.setEvent(MviContract.Event.OnRefreshHeadlineClicked)
        }
    }

    private fun initNavigation() {
        articlesViewModel.openArticleWebEvent.observe(viewLifecycleOwner, EventObserver { url ->
            goToArticleWebScreen(url)
        })
    }

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
            articlesViewModel.uiState.collect {
                when (it.headlineState) {
                    is MviContract.HeadlineState.Idle -> {
                        articlesViewModel.setLoading(false)
                    }
                    is MviContract.HeadlineState.Loading -> {
                        articlesViewModel.setLoading(true)
                    }
                    is MviContract.HeadlineState.Success -> {
                        articlesViewModel.setLoading(false)
                        articlesViewModel.setArticles(it.headlineState.headline.articles)
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            articlesViewModel.effect.collect {
                when (it) {
                    is MviContract.Effect.ShowToast -> {
                        articlesViewModel.setArticles(emptyList())
                        Toast.makeText(this@ArticlesFragment.context, "An error has occurred", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun goToArticleWebScreen(url: String) {
        val action = ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailFragment(url)
        findNavController().navigate(action)
    }
}
