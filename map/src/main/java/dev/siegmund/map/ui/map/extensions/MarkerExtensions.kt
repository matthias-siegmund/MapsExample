package dev.siegmund.map.ui.map.extensions

import com.google.android.gms.maps.model.Marker
import dev.siegmund.map.api.model.Scooter

fun Marker.scooter() = this.tag as Scooter