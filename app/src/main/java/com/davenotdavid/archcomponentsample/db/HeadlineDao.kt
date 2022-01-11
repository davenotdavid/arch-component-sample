package com.davenotdavid.archcomponentsample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davenotdavid.archcomponentsample.db.model.DbHeadline

@Dao
interface HeadlineDao {

    @Query("DELETE from headline")
    fun deleteAllHeadlines()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeadline(dbHeadline: DbHeadline)

    @Query("SELECT * from headline")
    fun getHeadline(): DbHeadline

}
