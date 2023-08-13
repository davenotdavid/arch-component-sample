package com.davenotdavid.archcomponentsample.api

import android.content.Context
import com.davenotdavid.archcomponentsample.BuildConfig
import com.davenotdavid.archcomponentsample.db.HeadlineDao
import com.davenotdavid.archcomponentsample.model.Headline
import com.davenotdavid.archcomponentsample.util.extensions.isNetworkConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import java.util.UUID
import javax.inject.Inject

class NewsApiRepository @Inject constructor(@ApplicationContext private val appContext: Context,
                                            private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
                                            private val service: NewsApiService,
                                            private val headlineDao: HeadlineDao)
{

    /**
     * Returns fresh headline data if there's network connection. Otherwise, cached
     * data from the DB is returned (although URL image loading and opening web
     * views isn't as supported in offline mode).
     */
    @Throws(Exception::class)
    suspend fun getHeadlines(
        type: String,
        category: String
    ): Headline {
        return withContext(dispatcher) {
            if (appContext.isNetworkConnected()) {
                val headline = service.getHeadlinesAsync(type, category, BuildConfig.NEWS_API_KEY).await()
                // Ugly, but gets the job done for assigning IDs here since the service doesn't
                // include them.
                headline.id = UUID.randomUUID().toString()
                headline.articles.map { it.id = UUID.randomUUID().toString() }

                // Updates cache.
                headlineDao.insertHeadline(headline.toDbHeadline())

                return@withContext headline
            } else {
                headlineDao.getHeadline().toHeadline()
            }
        }
    }

}
