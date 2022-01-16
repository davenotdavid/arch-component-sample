package com.davenotdavid.archcomponentsample.ui.articledetail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.davenotdavid.archcomponentsample.databinding.FragmentArticleDetailBinding

class ArticleDetailFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding
    private val articleDetailArgs: ArticleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupArticleWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupArticleWebView() {
        binding.articlePageWebView.apply {
            settings.javaScriptEnabled = true

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding.articlePageProgressBar.progressvisibility = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.articlePageProgressBar.progressvisibility = false
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    binding.articlePageProgressBar.progressvisibility = false
                }
            }

            loadUrl(articleDetailArgs.articleUrl)
        }
    }

}
