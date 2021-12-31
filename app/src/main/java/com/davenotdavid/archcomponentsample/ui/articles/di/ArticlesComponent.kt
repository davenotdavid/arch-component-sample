package com.davenotdavid.archcomponentsample.ui.articles.di

import com.davenotdavid.archcomponentsample.ui.articles.ArticlesFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ArticlesModule::class
    ]
)
interface ArticlesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ArticlesComponent
    }

    fun inject(articlesFragment: ArticlesFragment)

}
