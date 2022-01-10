package com.davenotdavid.archcomponentsample.app

import android.app.Application
import com.davenotdavid.archcomponentsample.di.AppComponent
import com.davenotdavid.archcomponentsample.di.DaggerAppComponent

class MyApplication : Application() {

    // Instance of the AppComponent that is used across the whole app
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    /**
     * Creates an instance of AppComponent using its Factory constructor.
     * We pass the applicationContext that will be used as Context in the graph.
     */
    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }

}
