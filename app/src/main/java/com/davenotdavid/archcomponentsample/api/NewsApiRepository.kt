package com.davenotdavid.archcomponentsample.api

import android.content.Context
import com.davenotdavid.archcomponentsample.BuildConfig
import com.davenotdavid.archcomponentsample.db.HeadlineDao
import com.davenotdavid.archcomponentsample.model.HeadlineResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.davenotdavid.archcomponentsample.util.extensions.isNetworkConnected
import javax.inject.Inject

/**
 * This repo instance used in say, View Models, are constructor-injected via
 * Hilt's View Model annotation.
 */
class NewsApiRepository @Inject constructor(private val context: Context,
                                            private val service: NewsApiService,
                                            private val headlineDao: HeadlineDao)
{

    @Throws(Exception::class)
    suspend fun getHeadlines(type: String,
                             category: String): HeadlineResponse
    {
        if (context.isNetworkConnected()) {
            return withContext(Dispatchers.IO) {
                service.getHeadlinesAsync(type, category, BuildConfig.NEWS_API_KEY).await()
            }
        } else {
            return headlineDao.getHeadlines().first().toHeadlineResponse()
        }
    }

}
