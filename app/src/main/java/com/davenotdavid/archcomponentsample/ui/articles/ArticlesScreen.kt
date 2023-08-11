package com.davenotdavid.archcomponentsample.ui.articles

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.davenotdavid.archcomponentsample.R
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.model.Headline
import com.davenotdavid.archcomponentsample.model.MviContract
import com.davenotdavid.archcomponentsample.model.Source
import com.davenotdavid.archcomponentsample.ui.components.FullScreenLoading
import com.davenotdavid.archcomponentsample.ui.compose.theme.ComposeAppTheme

/**
 * TODO: Update README
 * TODO: Swipe refresh layout
 * TODO: Coroutine scope placement?
 * TODO: Decouple Composable components
 * TODO: Lazy list state?
 * TODO: Try Live Edit with Studio version Giraffe
 * TODO: Testing
 */
@Composable
fun ArticlesScreen(
    modifier: Modifier = Modifier,
    headlineState: MviContract.HeadlineState,
    onArticleClick: (Article) -> Unit,
) {
    when (headlineState) {
        is MviContract.HeadlineState.Loading -> {
            FullScreenLoading()
        }
        is MviContract.HeadlineState.Idle -> {
            // TODO
            Log.d("tag", "Idle")
        }
        is MviContract.HeadlineState.Success -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                val headline = headlineState.headline

                items(items = headline.articles, key = { it.id }) { article ->
                    Column(modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable {
                            onArticleClick(article)
                        }
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val placeholder = painterResource(id = R.drawable.ic_placeholder)
                            AsyncImage(
                                modifier = modifier.weight(0.3f),
                                model = article.urlToImage,
                                contentDescription = "Article Image",
                                error = placeholder,
                                fallback = placeholder
                            )

                            Column(
                                modifier = modifier
                                    .weight(0.7f)
                                    .padding(start = 8.dp)
                            ) {
                                Text(
                                    text = article.title,
                                    fontSize = 16.sp
                                )

                                Text(
                                    text = article.publishedAt,
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewArticlesScreen() {
    ComposeAppTheme {
        val dummyHeadline = Headline(
            id = "1",
            status = "ok",
            totalResults = 7618,
            articles = listOf(
                Article(
                    id = "11",
                    source = Source(
                        id = "111",
                        name = "MakeUseOf"
                    ),
                    author = "Emma Garofalo",
                    title = "Tesla Updates Passenger Play to Stop Drivers Playing Games While Driving",
                    description = "Tesla is updating its Passenger Play feature to ensure drivers and passengers alike can only play games while the car is parked.",
                    url = "https://www.makeuseof.com/tesla-updates-passenger-play-feature/",
                    urlToImage = "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2021/12/tesla-passenger-play.jpg",
                    publishedAt = "2021-12-26T16:40:20Z",
                    content = "After a National Highway Traffic Safety Administration (NHTSA) review, Tesla is officially pulling the plug on gaming while driving.\r\nThe company's controversial Passenger Play feature is being diale… [+2486 chars]"
                ),
                Article(
                    id = "22",
                    source = Source(
                        id = "222",
                        name = "MakeUseOf"
                    ),
                    author = "Emma Garofalo",
                    title = "Tesla Updates Passenger Play to Stop Drivers Playing Games While Driving",
                    description = "Tesla is updating its Passenger Play feature to ensure drivers and passengers alike can only play games while the car is parked.",
                    url = "https://www.makeuseof.com/tesla-updates-passenger-play-feature/",
                    urlToImage = "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2021/12/tesla-passenger-play.jpg",
                    publishedAt = "2021-12-26T16:40:20Z",
                    content = "After a National Highway Traffic Safety Administration (NHTSA) review, Tesla is officially pulling the plug on gaming while driving.\r\nThe company's controversial Passenger Play feature is being diale… [+2486 chars]"
                ),
                Article(
                    id = "33",
                    source = Source(
                        id = "333",
                        name = "MakeUseOf"
                    ),
                    author = "Emma Garofalo",
                    title = "Tesla Updates Passenger Play to Stop Drivers Playing Games While Driving",
                    description = "Tesla is updating its Passenger Play feature to ensure drivers and passengers alike can only play games while the car is parked.",
                    url = "https://www.makeuseof.com/tesla-updates-passenger-play-feature/",
                    urlToImage = "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2021/12/tesla-passenger-play.jpg",
                    publishedAt = "2021-12-26T16:40:20Z",
                    content = "After a National Highway Traffic Safety Administration (NHTSA) review, Tesla is officially pulling the plug on gaming while driving.\r\nThe company's controversial Passenger Play feature is being diale… [+2486 chars]"
                ),
                Article(
                    id = "44",
                    source = Source(
                        id = "444",
                        name = "MakeUseOf"
                    ),
                    author = "Emma Garofalo",
                    title = "Tesla Updates Passenger Play to Stop Drivers Playing Games While Driving",
                    description = "Tesla is updating its Passenger Play feature to ensure drivers and passengers alike can only play games while the car is parked.",
                    url = "https://www.makeuseof.com/tesla-updates-passenger-play-feature/",
                    urlToImage = "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2021/12/tesla-passenger-play.jpg",
                    publishedAt = "2021-12-26T16:40:20Z",
                    content = "After a National Highway Traffic Safety Administration (NHTSA) review, Tesla is officially pulling the plug on gaming while driving.\r\nThe company's controversial Passenger Play feature is being diale… [+2486 chars]"
                ),
                Article(
                    id = "55",
                    source = Source(
                        id = "555",
                        name = "MakeUseOf"
                    ),
                    author = "Emma Garofalo",
                    title = "Tesla Updates Passenger Play to Stop Drivers Playing Games While Driving",
                    description = "Tesla is updating its Passenger Play feature to ensure drivers and passengers alike can only play games while the car is parked.",
                    url = "https://www.makeuseof.com/tesla-updates-passenger-play-feature/",
                    urlToImage = "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2021/12/tesla-passenger-play.jpg",
                    publishedAt = "2021-12-26T16:40:20Z",
                    content = "After a National Highway Traffic Safety Administration (NHTSA) review, Tesla is officially pulling the plug on gaming while driving.\r\nThe company's controversial Passenger Play feature is being diale… [+2486 chars]"
                )
            )
        )

        ArticlesScreen(
            headlineState = MviContract.HeadlineState.Success(dummyHeadline),
            onArticleClick = {}
        )
    }
}
