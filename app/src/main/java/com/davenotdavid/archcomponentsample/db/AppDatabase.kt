package com.davenotdavid.archcomponentsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davenotdavid.archcomponentsample.db.model.DbHeadline
import com.davenotdavid.archcomponentsample.db.typeconverter.ListArticleTypeConverter

@Database(entities = [DbHeadline::class], version = 1)
@TypeConverters(ListArticleTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun headlineDao(): HeadlineDao

    // TODO: Consider removing
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        /**
         * TODO: When migrating, take the following lines of commented code as a sample migration implementation
         *
         * Migration docs: https://developer.android.com/training/data-storage/room/migrating-db-versions
         */
        private fun buildDatabase(context: Context): AppDatabase {
            /*val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE questions ADD COLUMN testStr TEXT")
                }
            }

            val MIGRATION_2_3 = object : Migration(2, 3) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE questions ADD COLUMN testInt INTEGER")
                }
            }*/

            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_db_dev")
                //.addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()
        }
    }

}
