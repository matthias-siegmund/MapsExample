package dev.siegmund.map.model

import dev.siegmund.core.rx.SchedulerConfiguration
import io.reactivex.schedulers.Schedulers

object TestSchedulers {
    val schedulers = object : SchedulerConfiguration {
        override fun io() = Schedulers.trampoline()

        override fun computation() = Schedulers.trampoline()

        override fun ui() = Schedulers.trampoline()
    }
}
