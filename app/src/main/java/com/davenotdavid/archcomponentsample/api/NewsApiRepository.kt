package com.davenotdavid.archcomponentsample.api

import android.content.Context
import com.davenotdavid.archcomponentsample.BuildConfig
import com.davenotdavid.archcomponentsample.db.HeadlineDao
import com.davenotdavid.archcomponentsample.model.HeadlineResponse
import com.davenotdavid.archcomponentsample.util.extensions.isNetworkConnected
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class NewsApiRepository @Inject constructor(private val context: Context,
                                                 private val service: NewsApiService,
                                                 private val headlineDao: HeadlineDao)
{

    fun getHeadlines(type: String,
                     category: String): Single<HeadlineResponse>
    {
        if (context.isNetworkConnected()) {
            return service.getHeadlines(type, category, BuildConfig.NEWS_API_KEY)
        } else {
            return Single.just(headlineDao.getHeadlines().first().toHeadlineResponse())
        }
    }

}
