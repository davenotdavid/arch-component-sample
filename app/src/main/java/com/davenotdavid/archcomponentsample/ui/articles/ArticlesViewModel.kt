package com.davenotdavid.archcomponentsample.ui.articles

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.davenotdavid.archcomponentsample.api.NewsApiRepository
import com.davenotdavid.archcomponentsample.model.BaseViewModel
import com.davenotdavid.archcomponentsample.model.MviContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(private val newsApiRepository: NewsApiRepository)
    : BaseViewModel<MviContract.Event, MviContract.State, MviContract.Effect>() {

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
     * TODO: Wire-up with swipe refresh layout composable?
     */
    fun onRefresh() {
        getHeadlines()
    }

    /**
     * TODO: Handle custom user queries for headlines to test more events
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
