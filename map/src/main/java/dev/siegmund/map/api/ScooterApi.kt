package dev.siegmund.map.api

import dev.siegmund.map.api.model.ScooterResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ScooterApi {
    @GET(SCOOTERS)
    fun getScooters(): Single<ScooterResponse>

    private companion object {
        const val SCOOTERS = "k2srm"
    }
}