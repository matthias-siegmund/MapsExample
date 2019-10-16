package dev.siegmund.map.api

import dev.siegmund.map.api.model.Scooter
import io.reactivex.Single

class ScooterRepositoryImpl(
    private val scooterApi: ScooterApi
) : ScooterRepository {
    override fun getScooters(): Single<List<Scooter>> {
        return scooterApi.getScooters()
            .map { it.data.current }
    }

    override fun getScootersForPickup(): Single<List<Scooter>> {
        return scooterApi.getScooters()
            .map { it.data.current.filter { scooter-> scooter.state == "TO_PICKUP" } }
    }
}