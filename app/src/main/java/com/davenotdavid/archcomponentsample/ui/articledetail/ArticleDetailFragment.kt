package com.davenotdavid.archcomponentsample.ui.articledetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.davenotdavid.archcomponentsample.databinding.FragmentArticleDetailBinding

class ArticleDetailFragment : Fragment() {

    private lateinit var articleDetailBinding: FragmentArticleDetailBinding
    private val articleDetailArgs: ArticleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleDetailBinding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return articleDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupArticleWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupArticleWebView() {
        articleDetailBinding.articlePageWebView.apply {
            settings.javaScriptEnabled = true
            loadUrl(articleDetailArgs.articleUrl)
        }
    }

}
