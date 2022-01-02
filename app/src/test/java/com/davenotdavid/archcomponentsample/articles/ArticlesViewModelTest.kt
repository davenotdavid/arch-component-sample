package com.davenotdavid.archcomponentsample.articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.davenotdavid.archcomponentsample.api.NewsApiRepository
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.model.HeadlineResponse
import com.davenotdavid.archcomponentsample.model.Source
import com.davenotdavid.archcomponentsample.ui.articles.ArticlesViewModel
import com.davenotdavid.archcomponentsample.util.LiveDataTestUtil
import com.davenotdavid.archcomponentsample.util.scheduler.TrampolineSchedulerProvider
import com.google.common.truth.Truth.assertThat
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticlesViewModelTest {

    @Mock
    private lateinit var newsApiRepo: NewsApiRepository

    private lateinit var articlesViewModel: ArticlesViewModel
    private var schedulerProvider = TrampolineSchedulerProvider()

    // Executes each task synchronously using Architecture Components. Useful for updating
    // a LiveData's value for instance.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        articlesViewModel = ArticlesViewModel(newsApiRepo, schedulerProvider)
    }

    @Test
    fun getAllHeadlinesFromRepo_loadingStateAndDataLoaded() {
        val headlineDummy = HeadlineResponse(
            status = "ok",
            totalResults = 1,
            articles = listOf(
                Article(
                    source = Source(id = null, name = "Test Source"),
                    author = "",
                    title = "Test Article Title",
                    description = "",
                    url = "https://www.google.com",
                    urlToImage = "",
                    publishedAt = "2021-01-01T00:00:00+00:00",
                    content = ""
                )
            )
        )

        // TODO: Start mocking/stubbing service calls here?
//        articlesViewModel.getHeadlines()
        `when`(newsApiRepo.getHeadlines(type = "everything", category = "tesla")).thenReturn(Single.just(headlineDummy))

        // TODO: Or use `verify()` instead?

        // Then progress indicator is shown
        assertThat(LiveDataTestUtil.getValue(articlesViewModel.dataLoading)).isTrue()

        // TODO: Finish dispatching multi-threading here

        // Then progress indicator is hidden
        assertThat(LiveDataTestUtil.getValue(articlesViewModel.dataLoading)).isFalse()

        // And data correctly loaded
        assertThat(LiveDataTestUtil.getValue(articlesViewModel.articles)).hasSize(1)
    }

    // TODO: Test empty state with a successful response, but empty list?

    // TODO: Test empty state with a network error?

}
