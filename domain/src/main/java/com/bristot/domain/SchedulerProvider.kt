package com.bristot.domain

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun main(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler
}