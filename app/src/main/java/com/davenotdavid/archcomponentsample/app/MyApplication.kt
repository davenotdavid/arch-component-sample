package com.davenotdavid.archcomponentsample.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * An application with @HiltAndroidApp that triggers Hilt's code generation and
 * adds an application-level dependency container.
 */
@HiltAndroidApp
class MyApplication : Application()
