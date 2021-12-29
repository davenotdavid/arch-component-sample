package com.davenotdavid.archcomponentsample.ui.home.di

import androidx.lifecycle.ViewModel
import com.davenotdavid.archcomponentsample.dagger.ViewModelKey
import com.davenotdavid.archcomponentsample.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModel(viewModel: HomeViewModel): ViewModel

}
