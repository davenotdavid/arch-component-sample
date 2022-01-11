package com.davenotdavid.archcomponentsample.di

import android.content.Context
import androidx.room.Room
import com.davenotdavid.archcomponentsample.BuildConfig
import com.davenotdavid.archcomponentsample.api.NewsApiService
import com.davenotdavid.archcomponentsample.db.AppDatabase
import com.davenotdavid.archcomponentsample.db.HeadlineDao
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module to tell Hilt how to provide instances of types that cannot be constructor-injected.
 *
 * As these types are scoped to the application lifecycle using @Singleton, they're installed
 * in Hilt's ApplicationComponent.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

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
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(clientBuilder.build())
            .build()
            .create(NewsApiService::class.java)
    }

    /**
     * TODO: Handle DB migrations here?
     */
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
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

        return Room.databaseBuilder(appContext, AppDatabase::class.java, "app_db_dev")
            //.addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
    }

    @Singleton
    @Provides
    fun provideHeadlineDao(db: AppDatabase): HeadlineDao = db.headlineDao()
}
