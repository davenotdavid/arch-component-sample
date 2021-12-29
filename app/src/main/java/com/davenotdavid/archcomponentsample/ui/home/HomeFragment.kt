package com.davenotdavid.archcomponentsample.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.davenotdavid.archcomponentsample.app.MyApplication
import com.davenotdavid.archcomponentsample.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeViewModel by viewModels<HomeViewModel> { viewModelFactory }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.headline.observe(viewLifecycleOwner, Observer { headline ->
            if (headline == null) {
                textView.text = "Error displaying results"
            } else {
                textView.text = "Total results: ${headline.totalResults}"
            }
        })
        return root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}