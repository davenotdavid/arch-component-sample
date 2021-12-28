package com.davenotdavid.archcomponentsample.api

import com.davenotdavid.archcomponentsample.model.HeadlineResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * TODO: An endpoint call like the following returns about 14906 results ideal for testing pagination?:
 *  https://newsapi.org/v2/everything?q=tesla
 */
interface NewsApiService {

    @GET("{path}")
    fun getHeadlines(@Path("path") path: String,
                     @Query("q") query: String,
                     @Query("apiKey") apiKey: String): Single<HeadlineResponse>

}
