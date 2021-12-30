package com.davenotdavid.archcomponentsample.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.davenotdavid.archcomponentsample.app.MyApplication
import com.davenotdavid.archcomponentsample.databinding.FragmentHomeBinding
import com.davenotdavid.archcomponentsample.ui.home.adapter.HomeAdapter
import javax.inject.Inject

/**
 * TODO: Rename this MVVM package when ready
 */
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeViewModel by viewModels<HomeViewModel> { viewModelFactory }
    private lateinit var homeDataBinding: FragmentHomeBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Obtaining the home graph from the application class and instantiate
        // the @Inject fields with objects from the graph
        (requireActivity().application as MyApplication).appComponent.homeComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeDataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewmodel = homeViewModel
        }
        return homeDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sets the lifecycle owner to observe LiveData changes in this binding that
        // then updates the UI.
        homeDataBinding.lifecycleOwner = this.viewLifecycleOwner

        setupArticleAdapter()
    }

    override fun onDestroyView() {
        homeViewModel.clearSubs()
        super.onDestroyView()
    }

    private fun setupArticleAdapter() {
        val viewModel = homeDataBinding.viewmodel
        if (viewModel != null) {
            val adapter = HomeAdapter(viewModel)
            homeDataBinding.headlineArticleList.adapter = adapter
        } else {
            Log.w("tag", "Failed to set up article adapter with ViewModel")
        }
    }
}
