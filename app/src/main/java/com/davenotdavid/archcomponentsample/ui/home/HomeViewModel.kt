package com.davenotdavid.archcomponentsample.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davenotdavid.archcomponentsample.api.NewsApiRepository
import com.davenotdavid.archcomponentsample.model.HeadlineResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val newsApiRepository: NewsApiRepository) : ViewModel() {

    private val _headline = MutableLiveData<HeadlineResponse?>()
    val headline: LiveData<HeadlineResponse?> = _headline

    init {
        getHeadlines()
    }

    private fun getHeadlines() = viewModelScope.launch {
        try {
            val headlineResponse = newsApiRepository.getHeadlines(type = "everything", "tesla")
            _headline.value = headlineResponse
        } catch (ex: Exception) {
            Log.e("TAG", "Exception $ex")
            _headline.value = null
        }
    }
}
