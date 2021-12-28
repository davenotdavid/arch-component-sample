package com.davenotdavid.archcomponentsample.dagger

import com.davenotdavid.archcomponentsample.ui.home.di.HomeComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Definition of the Application graph.
 * The "modules" attribute in the @Component annotation tells Dagger what Modules
 * to include when building the graph
 *
 * Including SubcomponentsModule, tell ApplicationComponent that
 * LoginComponent is its subcomponent.
 */
@Singleton
@Component(modules = [AppModule::class, SubcomponentsModule::class])
interface AppComponent {

    // Don't need to inject `MainActivity` here anymore since the subcomponent will handle that now

    // This function exposes the HomeComponent Factory out of the graph so consumers
    // can use it to obtain new instances of HomeComponent
    fun homeComponent(): HomeComponent.Factory

}
