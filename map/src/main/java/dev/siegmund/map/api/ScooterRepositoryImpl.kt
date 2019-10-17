package dev.siegmund.map.api

class ScooterRepositoryImpl(
    private val scooterApi: ScooterApi
) : ScooterRepository {
    override fun getScooters() = scooterApi.getScooters().map { response ->
        response.data.current
    }

    override fun getScootersForPickup() = scooterApi.getScooters().map { response ->
        response.data.current.filter { scooter ->
            scooter.state == "TO_PICKUP"
        }
    }
}