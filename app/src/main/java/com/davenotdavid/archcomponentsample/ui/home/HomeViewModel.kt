package com.davenotdavid.archcomponentsample.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davenotdavid.archcomponentsample.api.NewsApiRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val newsApiRepository: NewsApiRepository) : ViewModel() {

    private val _headlineResults = MutableLiveData<String>()
    val headlineResults: LiveData<String> = _headlineResults

    private val disposables = CompositeDisposable()

    init {
        getHeadlines()
    }

    /**
     * TODO: Invoked only when returning back to [HomeFragment] prior to re-running the MVVM flow?
     *  - go as far as forcefully clearing the disposables from the View layer?
     */
    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    private fun getHeadlines() {
        disposables.add(newsApiRepository.getHeadlines(type = "everything", "tesla")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { headlineResponse ->
                    _headlineResults.value = headlineResponse.totalResults.toString()
                },
                { throwable ->
                    Log.e("TAG", "Error: $throwable")
                    _headlineResults.value = "0"
                }
            )
        )
    }
}
