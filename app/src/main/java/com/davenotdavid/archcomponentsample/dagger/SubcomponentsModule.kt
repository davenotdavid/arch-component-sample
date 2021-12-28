package com.davenotdavid.archcomponentsample.dagger

import com.davenotdavid.archcomponentsample.ui.home.di.HomeComponent
import dagger.Module

// The "subcomponents" attribute in the @Module annotation tells Dagger what
// Subcomponents are children of the Component this module is included in.
@Module(subcomponents = [HomeComponent::class])
class SubcomponentsModule {

}
