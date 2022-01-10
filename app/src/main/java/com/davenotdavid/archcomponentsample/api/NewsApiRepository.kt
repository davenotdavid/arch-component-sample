package com.davenotdavid.archcomponentsample.api

import android.content.Context
import com.davenotdavid.archcomponentsample.BuildConfig
import com.davenotdavid.archcomponentsample.db.HeadlineDao
import com.davenotdavid.archcomponentsample.db.model.DbHeadline
import com.davenotdavid.archcomponentsample.model.Headline
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class NewsApiRepository @Inject constructor(private val context: Context,
                                                 private val service: NewsApiService,
                                                 private val headlineDao: HeadlineDao)
{

    /**
     * TODO: Get [Headline] DB data at first, otherwise service call?
     */
    fun getHeadlines(
        type: String,
        category: String,
        onSuccessCallback: (Headline) -> Unit,
        onErrorCallback: (Any?) -> Unit): Disposable
    {
        return headlineDao.getHeadline()
            .onErrorResumeNext(service.getHeadlines(type, category, BuildConfig.NEWS_API_KEY).map {
                it.toDbHeadline()
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { dbHeadline ->
                    headlineDao.insertHeadline(dbHeadline)

                    onSuccessCallback.invoke(dbHeadline.toHeadline())
                },
                { throwable ->
                    onErrorCallback.invoke(throwable)
                }
            )
//        if (context.isNetworkConnected()) {
//            return service.getHeadlines(type, category, BuildConfig.NEWS_API_KEY)
//        } else {
//            return Single.just(headlineDao.getHeadline().toHeadlineResponse())
//        }
    }

}
