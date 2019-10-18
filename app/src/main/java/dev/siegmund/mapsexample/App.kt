package dev.siegmund.mapsexample

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.siegmund.mapsexample.di.DaggerAppComponent
import dev.siegmund.core.di.CoreComponent
import dev.siegmund.core.di.CoreComponentProvider
import dev.siegmund.core.di.DaggerCoreComponent
import timber.log.Timber

class App : DaggerApplication(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .coreComponent(provideCoreComponent())
            .build()
    }

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent
                .builder()
                .build()
        }
        return coreComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}