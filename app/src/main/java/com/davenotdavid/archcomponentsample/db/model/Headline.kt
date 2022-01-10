package com.davenotdavid.archcomponentsample.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.model.HeadlineResponse

@Entity(tableName = "headline")
data class Headline(

    /**
     * TODO: Generate own key?
     * TODO: How to map to response model?
     */
    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "status")
    val status: String? = null,

    @ColumnInfo(name = "totalResults")
    val totalResults: Int? = null,

    @ColumnInfo(name = "articles")
    val articles: List<Article>? = null

) {

    /**
     * TODO
     */
    fun toHeadlineResponse(): HeadlineResponse = HeadlineResponse(
        status!!,
        totalResults!!,
        articles!!
    )

}
