package com.davenotdavid.archcomponentsample.dagger

import android.content.Context
import com.davenotdavid.archcomponentsample.ui.home.di.HomeComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Main component for the application.
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelBuilderModule::class,
        SubcomponentsModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    // This function exposes the HomeComponent Factory out of the graph so consumers
    // can use it to obtain new instances of HomeComponent
    fun homeComponent(): HomeComponent.Factory

}
