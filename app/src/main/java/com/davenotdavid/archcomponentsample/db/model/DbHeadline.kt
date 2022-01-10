package com.davenotdavid.archcomponentsample.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.model.Headline

@Entity(tableName = "headline")
data class DbHeadline(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "status")
    val status: String? = null,

    @ColumnInfo(name = "totalResults")
    val totalResults: Int? = null,

    @ColumnInfo(name = "articles")
    val articles: List<Article>? = null

) {

    /**
     * TODO: How to properly handle nullability above?
     */
    fun toHeadline(): Headline = Headline(
        id,
        status!!,
        totalResults!!,
        articles!!
    )

}
