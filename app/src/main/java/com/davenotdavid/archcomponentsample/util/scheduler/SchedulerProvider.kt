package com.davenotdavid.archcomponentsample.util.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Implementation of the [BaseSchedulerProvider] using the default [Schedulers].
 */
class SchedulerProvider : BaseSchedulerProvider {

    override fun computation() = Schedulers.computation()

    override fun ui() = AndroidSchedulers.mainThread()

    override fun io() = Schedulers.io()

}
