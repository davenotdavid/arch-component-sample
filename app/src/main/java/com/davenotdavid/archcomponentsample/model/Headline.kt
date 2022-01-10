package com.davenotdavid.archcomponentsample.model

import com.davenotdavid.archcomponentsample.db.model.DbHeadline
import java.util.UUID

/**
 * Example response:
{
    "status": "ok",
    "totalResults": 7618,
    "articles": [
        {
            "source": {
                "id": null,
                "name": "MakeUseOf"
            },
            "author": "Emma Garofalo",
            "title": "Tesla Updates Passenger Play to Stop Drivers Playing Games While Driving",
            "description": "Tesla is updating its Passenger Play feature to ensure drivers and passengers alike can only play games while the car is parked.",
            "url": "https://www.makeuseof.com/tesla-updates-passenger-play-feature/",
            "urlToImage": "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2021/12/tesla-passenger-play.jpg",
            "publishedAt": "2021-12-26T16:40:20Z",
            "content": "After a National Highway Traffic Safety Administration (NHTSA) review, Tesla is officially pulling the plug on gaming while driving.\r\nThe company's controversial Passenger Play feature is being diale… [+2486 chars]"
        },

        ...

    ]
}
 */
data class Headline(
    // Assign its own ID since the News API service doesn't return one
    val id: String = UUID.randomUUID().toString(),
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) {

    fun toDbHeadline(): DbHeadline = DbHeadline(
        id,
        status,
        totalResults,
        articles
    )
}

data class Source(
    val id: String?,
    val name: String
)

data class Article(
    // Assign its own ID since the News API service doesn't return one
    val id: String = UUID.randomUUID().toString(),
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    // UTC timestamp (e.g. 2021-12-26T16:40:20Z)
    val publishedAt: String,
    val content: String
)
