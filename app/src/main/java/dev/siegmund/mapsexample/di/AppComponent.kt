package dev.siegmund.mapsexample.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.siegmund.mapsexample.App
import dev.siegmund.core.di.CoreComponent

@Component(
    modules = [
        AppModule::class,
        BindingModule::class,
        AndroidSupportInjectionModule::class
    ],
    dependencies = [CoreComponent::class]
)
@AppScope
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): AppComponent
    }
}