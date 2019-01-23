package com.bristot.domain.extensions

import com.bristot.domain.SchedulerProvider
import io.reactivex.Single

fun <T> Single<T>.defaultSched(schedulerProvider: SchedulerProvider): Single<T> {
    return this.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.main())
}