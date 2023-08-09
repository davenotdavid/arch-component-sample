package com.davenotdavid.archcomponentsample.ui.articles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davenotdavid.archcomponentsample.api.NewsApiRepository
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.model.BaseViewModel
import com.davenotdavid.archcomponentsample.model.MviContract
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * TODO: Finalize
 *
 * Identifies a [ViewModel] for construction injection.
 *
 * The ViewModel annotated with HiltViewModel will be available for creation
 * by the [HiltViewModelFactory] and can be retrieved by default in an Activity/Fragment
 * annotated with [AndroidEntryPoint]. The HiltViewModel containing a constructor
 * annotated with [Inject] will have its dependencies defined in the constructor params
 * injected by Dagger's Hilt.
 */
@HiltViewModel
class ArticlesViewModel @Inject constructor(private val newsApiRepository: NewsApiRepository)
    : BaseViewModel<MviContract.Event, MviContract.State, MviContract.Effect>() {

    // Inits LiveData val to an empty list to avoid a null-pointer when data binding adapter.
    private val _articles = MutableLiveData<List<Article>>().apply { value = emptyList() }
    val articles: LiveData<List<Article>> = _articles

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

//    private val _openArticleWebEvent = MutableLiveData<Event<String>>()
//    val openArticleWebEvent: LiveData<Event<String>> = _openArticleWebEvent

    init {
        getHeadlines()
    }

    /**
     * Creates an initial State of Views
     */
    override fun createInitialState(): MviContract.State {
        return MviContract.State(
            MviContract.HeadlineState.Loading
        )
    }

    override fun handleEvent(event: MviContract.Event) {
        when (event) {
            is MviContract.Event.OnRefreshHeadlineClicked -> {
                getHeadlines()
            }
            is MviContract.Event.OnShowToastClicked -> {
                setEffect {
                    MviContract.Effect.ShowToast
                }
            }
        }
    }

    /**
     * TODO: Remove
     *
     * Called by Data Binding via [ArticlesAdapter].
     */
    fun openArticleWebView(url: String) {
//        _openArticleWebEvent.value = Event(url)
    }

    /**
     * Data binding with [SwipeRefreshLayout]'s public functions/callbacks to
     * invoke [onRefresh] below.
     */
    fun onRefresh() {
        getHeadlines()
    }

    /**
     * TODO: Temp
     */
    fun setLoading(loading: Boolean) {
        _dataLoading.value = loading
    }

    /**
     * TODO: Temp
     */
    fun setArticles(articles: List<Article>) {
        _articles.value = articles
    }

    /**
     * TODO: Finalize
     */
    private fun getHeadlines() = viewModelScope.launch {
        setState { copy(headlineState = MviContract.HeadlineState.Loading) }

        try {
            val headline = newsApiRepository.getHeadlines(type = "everything", "tesla")
            setState { copy(headlineState = MviContract.HeadlineState.Success(headline)) }
        } catch (ex: Exception) {
            Log.e("TAG", "Exception $ex")

            setState { copy(headlineState = MviContract.HeadlineState.Idle) }
            setEffect { MviContract.Effect.ShowToast }
        }
    }

}
