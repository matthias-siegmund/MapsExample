package dev.siegmund.map.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dev.siegmund.core.api.ApiInterceptor
import dev.siegmund.core.rx.SchedulerConfiguration
import dev.siegmund.map.api.ScooterApi
import dev.siegmund.map.api.ScooterRepository
import dev.siegmund.map.api.ScooterRepositoryImpl
import dev.siegmund.map.ui.model.MapViewModelFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MapModule {
    @Provides
    fun providesScooterApi(gson: Gson): ScooterApi {
        val apiClient = OkHttpClient.Builder().addInterceptor(ApiInterceptor()).build()
        return Retrofit.Builder().apply {
            baseUrl("https://api.myjson.com/bins/")
            addConverterFactory(GsonConverterFactory.create(gson))
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(apiClient)
        }.build().create(ScooterApi::class.java)
    }

    @Provides
    fun providesScooterRepository(
        scooterApi: ScooterApi
    ): ScooterRepository = ScooterRepositoryImpl(scooterApi)

    @Provides
    fun providesMapViewModelFactory(
        scooterRepository: ScooterRepository,
        schedulerConfiguration: SchedulerConfiguration
    ): MapViewModelFactory =
        MapViewModelFactory(
            scooterRepository,
            schedulerConfiguration
        )
}