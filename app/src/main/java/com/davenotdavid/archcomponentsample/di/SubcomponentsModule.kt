package com.davenotdavid.archcomponentsample.di

import com.davenotdavid.archcomponentsample.ui.articles.di.ArticlesComponent
import dagger.Module

// `subcomponents` tell Dagger what Subcomponents are children of the Component
// this module is included in.
@Module(
    subcomponents = [
        ArticlesComponent::class
    ]
)
object SubcomponentsModule
