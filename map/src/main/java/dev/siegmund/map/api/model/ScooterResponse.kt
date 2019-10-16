package dev.siegmund.map.api.model

import com.google.gson.annotations.SerializedName

data class ScooterResponse(
    @SerializedName("data") val data: ScooterResponseData
)