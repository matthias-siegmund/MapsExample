package dev.siegmund.mapsexample.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.siegmund.mapsexample.App
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun providesAppContext(application: App): Context {
        return application.applicationContext
    }
}