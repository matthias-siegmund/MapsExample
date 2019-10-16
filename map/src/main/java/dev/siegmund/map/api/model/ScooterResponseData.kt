package dev.siegmund.map.api.model

import com.google.gson.annotations.SerializedName

data class ScooterResponseData(
    @SerializedName("current") val current: List<Scooter>,
    @SerializedName("stats") val stats: Stats
)