package com.davenotdavid.archcomponentsample.util.scheduler

import androidx.annotation.NonNull
import io.reactivex.Scheduler

/**
 * Allow providing different types of [Scheduler]s that will be used in both
 * regular code and tests.
 */
interface BaseSchedulerProvider {

    @NonNull
    fun computation(): Scheduler

    @NonNull
    fun io(): Scheduler

    @NonNull
    fun ui(): Scheduler

}
