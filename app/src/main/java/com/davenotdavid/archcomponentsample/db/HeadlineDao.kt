package com.davenotdavid.archcomponentsample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davenotdavid.archcomponentsample.db.model.DbHeadline
import io.reactivex.Single

/**
 * TODO: [DbHeadline] root object, or [Articles]
 * TODO: ^ rename accordingly
 */
@Dao
interface HeadlineDao {

    @Query("DELETE from headline")
    fun deleteAllHeadlines()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeadline(dbHeadline: DbHeadline)

    /**
     * Returns just a [DbHeadline] with the list of [Article]s in it.
     */
    @Query("SELECT * from headline")
    fun getHeadline(): Single<DbHeadline>

}
