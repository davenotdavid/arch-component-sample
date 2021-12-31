package com.davenotdavid.archcomponentsample.ui.articles.di

import androidx.lifecycle.ViewModel
import com.davenotdavid.archcomponentsample.dagger.ViewModelKey
import com.davenotdavid.archcomponentsample.ui.articles.ArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ArticlesModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    abstract fun bindViewModel(viewModel: ArticlesViewModel): ViewModel

}
