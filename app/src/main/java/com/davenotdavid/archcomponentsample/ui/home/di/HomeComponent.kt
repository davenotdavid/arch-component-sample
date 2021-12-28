package com.davenotdavid.archcomponentsample.ui.home.di

import com.davenotdavid.archcomponentsample.MainActivity
import com.davenotdavid.archcomponentsample.dagger.ActivityScope
import com.davenotdavid.archcomponentsample.ui.home.HomeFragment
import dagger.Subcomponent

// Classes annotated with @ActivityScope are scoped to the graph and the same
// instance of that type is provided every time the type is requested.
@ActivityScope
@Subcomponent
interface HomeComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    // `MainActivity` and `HomeFragment` request injection from HomeComponent.
    // The graph needs to satisfy all the dependencies of the fields those
    // classes are injecting.
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)

}
