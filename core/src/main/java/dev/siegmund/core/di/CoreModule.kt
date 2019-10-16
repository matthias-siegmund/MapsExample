package dev.siegmund.core.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dev.siegmund.core.rx.SchedulerConfiguration
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class CoreModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun providesSchedulerConfiguration(): SchedulerConfiguration = object : SchedulerConfiguration {
        override fun io(): Scheduler = Schedulers.io()

        override fun computation(): Scheduler = Schedulers.computation()

        override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    }
}