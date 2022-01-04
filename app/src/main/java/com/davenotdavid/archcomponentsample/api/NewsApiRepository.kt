package com.davenotdavid.archcomponentsample.api

import com.davenotdavid.archcomponentsample.BuildConfig
import com.davenotdavid.archcomponentsample.model.HeadlineResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This repo instance used in say, View Models, are constructor-injected via
 * Hilt's View Model annotation.
 */
class NewsApiRepository @Inject constructor(private val service: NewsApiService) {

    @Throws(Exception::class)
    suspend fun getHeadlines(type: String,
                             category: String): HeadlineResponse
    {
        return withContext(Dispatchers.IO) {
            service.getHeadlinesAsync(type, category, BuildConfig.NEWS_API_KEY).await()
        }
    }

}
