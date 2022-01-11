package com.davenotdavid.archcomponentsample.ui.articles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.davenotdavid.archcomponentsample.databinding.FragmentArticlesBinding
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
    ): View? {
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

        setupArticleAdapter()
        setupNavigation()
    }

    private fun setupArticleAdapter() {
        val viewModel = homeDataBinding.viewmodel
        if (viewModel != null) {
            val adapter = ArticlesAdapter(viewModel)
            homeDataBinding.headlineArticleList.adapter = adapter
        } else {
            Log.w("tag", "Failed to set up article adapter with ViewModel")
        }
    }

    private fun setupNavigation() {
        articlesViewModel.openArticleWebEvent.observe(viewLifecycleOwner, EventObserver { url ->
            goToArticleWebScreen(url)
        })
    }

    private fun goToArticleWebScreen(url: String) {
        val action = ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailFragment(url)
        findNavController().navigate(action)
    }
}
