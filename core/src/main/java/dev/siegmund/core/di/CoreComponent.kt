package dev.siegmund.core.di

import com.google.gson.Gson
import dagger.Component
import dev.siegmund.core.rx.SchedulerConfiguration
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    fun provideOkHttpClient(): OkHttpClient

    fun provideGson(): Gson

    fun provideGsonConverterFactory(): GsonConverterFactory

    fun providesSchedulerConfiguration(): SchedulerConfiguration
}