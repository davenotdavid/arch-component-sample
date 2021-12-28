package com.davenotdavid.archcomponentsample.app

import android.app.Application
import com.davenotdavid.archcomponentsample.dagger.DaggerAppComponent

class MyApplication : Application() {

    // Reference to the application graph that is used across the whole app
    val appComponent = DaggerAppComponent.create()

}
