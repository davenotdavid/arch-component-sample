package com.davenotdavid.archcomponentsample.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.davenotdavid.archcomponentsample.MainActivity
import com.davenotdavid.archcomponentsample.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    // Fields that need to be injected by the home graph
    @Inject lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Obtaining the home graph from MainActivity and instantiate
        // the @Inject fields with objects from the graph
        (activity as MainActivity).homeComponent.inject(this)
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
        super.onDestroyView()
        _binding = null
    }
}