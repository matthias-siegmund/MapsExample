package dev.siegmund.map.api

import dev.siegmund.map.api.model.Scooter
import io.reactivex.Single

interface ScooterRepository {
    fun getScooters(): Single<List<Scooter>>

    fun getScootersForPickup(): Single<List<Scooter>>
}