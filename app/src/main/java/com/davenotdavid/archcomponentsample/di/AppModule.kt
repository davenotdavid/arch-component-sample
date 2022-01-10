package com.davenotdavid.archcomponentsample.di

import android.content.Context
import androidx.room.Room
import com.davenotdavid.archcomponentsample.BuildConfig
import com.davenotdavid.archcomponentsample.api.NewsApiService
import com.davenotdavid.archcomponentsample.db.AppDatabase
import com.davenotdavid.archcomponentsample.db.HeadlineDao
import com.davenotdavid.archcomponentsample.util.scheduler.BaseSchedulerProvider
import com.davenotdavid.archcomponentsample.util.scheduler.SchedulerProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideNewsApiRetrofitService(): NewsApiService {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(clientBuilder.build())
            .build()
            .create(NewsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider = SchedulerProvider()

    /**
     * TODO: DB migrations here?
     */
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
//        val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE questions ADD COLUMN testStr TEXT")
//            }
//        }
//
//        val MIGRATION_2_3 = object : Migration(2, 3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE questions ADD COLUMN testInt INTEGER")
//            }
//        }

        return Room.databaseBuilder(context, AppDatabase::class.java, "app_db_dev")
            //.addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
    }

    @Singleton
    @Provides
    fun provideHeadlineDao(db: AppDatabase): HeadlineDao {
        return db.headlineDao()
    }
}
