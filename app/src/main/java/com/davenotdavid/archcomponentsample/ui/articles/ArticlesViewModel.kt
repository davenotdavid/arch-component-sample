package com.davenotdavid.archcomponentsample.ui.articles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davenotdavid.archcomponentsample.api.NewsApiRepository
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(private val newsApiRepository: NewsApiRepository) : ViewModel() {

    // Inits LiveData val to an empty list to avoid a null-pointer when data binding adapter.
    private val _articles = MutableLiveData<List<Article>>().apply { value = emptyList() }
    val articles: LiveData<List<Article>> = _articles

    // TODO Live data for loading and error states
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _totalResults = MutableLiveData<String>()
    val totalResults: LiveData<String> = _totalResults

    private val _openArticleWebEvent = MutableLiveData<Event<String>>()
    val openArticleWebEvent: LiveData<Event<String>> = _openArticleWebEvent

    private val disposables = CompositeDisposable()

    init {
        getHeadlines()
    }

    /**
     * Forcefully removes subs since [onCleared] is invoked only when returning
     * back to the Fragment with this current arch.
     */
    fun clearSubs() {
        disposables.clear()
    }

    /**
     * Called by Data Binding via [ArticlesAdapter].
     */
    fun openArticleWebView(url: String) {
        _openArticleWebEvent.value = Event(url)
    }

    /**
     * Data binding with [SwipeRefreshLayout]'s public functions/callbacks to
     * invoke [onRefresh] below.
     */
    fun onRefresh() {
        getHeadlines()
    }

    private fun getHeadlines() {
        _dataLoading.value = true

        disposables.add(newsApiRepository.getHeadlines(type = "everything", "tesla")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { headlineResponse ->
                    _totalResults.value = headlineResponse.totalResults.toString()
                    _articles.value = headlineResponse.articles

                    _dataLoading.value = false
                },
                { throwable ->
                    Log.e("TAG", "Error: $throwable")
                    _totalResults.value = "0"
                    _articles.value = emptyList()

                    _dataLoading.value = false
                }
            )
        )
    }
}
