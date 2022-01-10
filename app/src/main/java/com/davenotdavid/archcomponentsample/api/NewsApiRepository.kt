package com.davenotdavid.archcomponentsample.api

import android.content.Context
import com.davenotdavid.archcomponentsample.BuildConfig
import com.davenotdavid.archcomponentsample.db.HeadlineDao
import com.davenotdavid.archcomponentsample.model.HeadlineResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.davenotdavid.archcomponentsample.util.extensions.isNetworkConnected
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * This repo instance used in say, View Models, are constructor-injected via
 * Hilt's View Model annotation.
 */
class NewsApiRepository @Inject constructor(@ApplicationContext private val appContext: Context,
                                            private val service: NewsApiService,
                                            private val headlineDao: HeadlineDao)
{

    /**
     * Returns fresh headline data if there's network connection. Otherwise, cached
     * data from the DB is returned.
     */
    @Throws(Exception::class)
    suspend fun getHeadlines(type: String,
                             category: String): HeadlineResponse
    {
        return withContext(Dispatchers.IO) {
            if (appContext.isNetworkConnected()) {
                service.getHeadlinesAsync(type, category, BuildConfig.NEWS_API_KEY).await()
            } else {
                headlineDao.getHeadlines().first().toHeadlineResponse()
            }
        }
    }

}
