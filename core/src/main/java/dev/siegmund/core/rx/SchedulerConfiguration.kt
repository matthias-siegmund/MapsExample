package dev.siegmund.core.rx

import io.reactivex.Scheduler

interface SchedulerConfiguration {
    fun io(): Scheduler

    fun computation(): Scheduler

    fun ui(): Scheduler
}