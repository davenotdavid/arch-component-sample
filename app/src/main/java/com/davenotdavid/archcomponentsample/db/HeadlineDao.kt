package com.davenotdavid.archcomponentsample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davenotdavid.archcomponentsample.db.model.Headline

/**
 * TODO: [Headline] root object, or [Articles]
 * TODO: ^ rename accordingly
 */
@Dao
interface HeadlineDao {

    @Query("DELETE from headline")
    fun deleteAllHeadlines()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeadline(headline: Headline)

    @Query("SELECT * from headline")
    fun getHeadlines(): List<Headline>

}
