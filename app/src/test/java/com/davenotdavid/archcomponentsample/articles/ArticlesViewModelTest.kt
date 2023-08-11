package com.davenotdavid.archcomponentsample.articles

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.davenotdavid.archcomponentsample.api.NewsApiRepository
import com.davenotdavid.archcomponentsample.api.NewsApiService
import com.davenotdavid.archcomponentsample.db.AppDatabase
import com.davenotdavid.archcomponentsample.db.HeadlineDao
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.model.Headline
import com.davenotdavid.archcomponentsample.model.Source
import com.davenotdavid.archcomponentsample.ui.articles.ArticlesViewModel
import com.davenotdavid.archcomponentsample.util.LiveDataTestUtil
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException
import java.util.UUID

/**
 * TODO: Update with MVI and Compose changes
 * TODO: Test DB CRUD functions here too?
 */
@RunWith(MockitoJUnitRunner::class)
class ArticlesViewModelTest {

    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var newsApiService: NewsApiService

    private lateinit var database: AppDatabase
    private lateinit var headlineDao: HeadlineDao
    private lateinit var newsApiRepo: NewsApiRepository
    private lateinit var articlesViewModel: ArticlesViewModel

    // Executes each task synchronously using Architecture Components. Useful for updating
    // a LiveData's value for instance.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        headlineDao = database.headlineDao()

        newsApiRepo = NewsApiRepository(context, Dispatchers.IO, newsApiService, headlineDao)
        articlesViewModel = ArticlesViewModel(newsApiRepo)
    }

    @Test
    fun getAllHeadlinesFromRepo_loadingStateAndDataLoaded() {
        val dummyHeadline = Headline(
            id = UUID.randomUUID().toString(),
            status = "ok",
            totalResults = 1,
            articles = listOf(
                Article(
                    id = UUID.randomUUID().toString(),
                    source = Source(id = "davenotdavid", name = "DaveNOTDavid"),
                    author = "Dave Park",
                    title = "Funemployed \uD83E\uDD37\u200D♂️",
                    description = "Oh no, Dave is funemployed even during the beginning of the New Year!",
                    url = "http://www.davenotdavid.com/blog/post_funemployed",
                    urlToImage = "http://www.davenotdavid.com/images/meme_2022_2020_too.jpeg",
                    publishedAt = "2022-01-03T00:00:00+00:00",
                    content = "Dave had reasons why he decided to resign from his previous role, and what his gameplan will be to start off '22 on a good note."
                )
            )
        )

        // TODO: Mock service call to eventually return the dummy response via the View Model.
//        `when`(newsApiRepo.getHeadlines(type = "everything", category = "tesla")).thenReturn(Single.just(dummyHeadline))
//        articlesViewModel.getHeadlines()

        // TODO: The following gets called after the response is returned, so fails
        // Then progress indicator is shown
//        assertThat(LiveDataTestUtil.getValue(articlesViewModel.dataLoading)).isTrue()
        // Then progress indicator is hidden
//        assertThat(LiveDataTestUtil.getValue(articlesViewModel.dataLoading)).isFalse()

        // And data correctly loaded
//        assertThat(LiveDataTestUtil.getValue(articlesViewModel.articles)).hasSize(1)
    }

    // TODO: Test empty state with a successful response, but empty list?

    // TODO: Test empty state with a network error?




    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

}
