package com.davenotdavid.archcomponentsample.ui.home.di

import com.davenotdavid.archcomponentsample.ui.home.HomeFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        HomeModule::class
    ]
)
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(homeFragment: HomeFragment)

}
