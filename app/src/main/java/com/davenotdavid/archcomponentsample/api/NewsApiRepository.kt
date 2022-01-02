package com.davenotdavid.archcomponentsample.api

import com.davenotdavid.archcomponentsample.BuildConfig
import com.davenotdavid.archcomponentsample.model.HeadlineResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class NewsApiRepository @Inject constructor(private val service: NewsApiService) {

    fun getHeadlines(type: String,
                     category: String): Single<HeadlineResponse>
    {
        return service.getHeadlines(type, category, BuildConfig.NEWS_API_KEY)
    }

}
