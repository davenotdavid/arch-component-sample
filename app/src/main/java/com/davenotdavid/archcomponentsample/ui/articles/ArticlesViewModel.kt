package com.davenotdavid.archcomponentsample.ui.articles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davenotdavid.archcomponentsample.api.NewsApiRepository
import com.davenotdavid.archcomponentsample.model.Article
import kotlinx.coroutines.launch
import com.davenotdavid.archcomponentsample.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Identifies a [ViewModel] for construction injection.
 *
 * The ViewModel annotated with HiltViewModel will be available for creation
 * by the [HiltViewModelFactory] and can be retrieved by default in an Activity/Fragment
 * annotated with [AndroidEntryPoint]. The HiltViewModel containing a constructor
 * annotated with [Inject] will have its dependencies defined in the constructor params
 * injected by Dagger's Hilt.
 */
@HiltViewModel
class ArticlesViewModel @Inject constructor(private val newsApiRepository: NewsApiRepository) : ViewModel() {

    // Inits LiveData val to an empty list to avoid a null-pointer when data binding adapter.
    private val _articles = MutableLiveData<List<Article>>().apply { value = emptyList() }
    val articles: LiveData<List<Article>> = _articles

    private val _totalResults = MutableLiveData<String>()
    val totalResults: LiveData<String> = _totalResults

    private val _openArticleWebEvent = MutableLiveData<Event<String>>()
    val openArticleWebEvent: LiveData<Event<String>> = _openArticleWebEvent

    init {
        getHeadlines()
    }

    /**
     * Forcefully removes subs since [onCleared] is invoked only when returning
     * back to the Fragment with this current arch.
     */
    fun clearSubs() {
        // TODO: Decide to remove Coroutine scope here since that appears to happen only when `onCleared()` is called
    }

    /**
     * Called by Data Binding via [ArticlesAdapter].
     */
    fun openArticleWebView(url: String) {
        _openArticleWebEvent.value = Event(url)
    }

    private fun getHeadlines() = viewModelScope.launch {
        try {
            val headlineResponse = newsApiRepository.getHeadlines(type = "everything", "tesla")
            _totalResults.value = headlineResponse.totalResults.toString()
            _articles.value = headlineResponse.articles
        } catch (ex: Exception) {
            Log.e("TAG", "Exception $ex")
            _totalResults.value = "0"
            _articles.value = emptyList()
        }
    }
}
