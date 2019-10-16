package dev.siegmund.core.rx.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable?.plus(d: Disposable) = this?.add(d)
    ?: throw IllegalStateException("Disposable has not been initialized.")