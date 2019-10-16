package dev.siegmund.map.api.model

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("open") val open: Int,
    @SerializedName("assigned") val assigned: Int,
    @SerializedName("resolved") val resolved: Int
)