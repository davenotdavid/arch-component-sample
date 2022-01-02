package com.davenotdavid.archcomponentsample.util.scheduler

import androidx.annotation.NonNull
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Implementation of the [BaseSchedulerProvider] making all [Scheduler]s trampoline
 * that will be used for tests.
 */
class TrampolineSchedulerProvider : BaseSchedulerProvider {

    @NonNull
    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

    @NonNull
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    @NonNull
    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }

}
