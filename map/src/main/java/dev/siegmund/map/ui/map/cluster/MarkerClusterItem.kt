package dev.siegmund.map.ui.map.cluster

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import dev.siegmund.map.api.model.Scooter

class MarkerClusterItem(
    val scooter: Scooter,
    private val position: LatLng,
    private val title: String
) : ClusterItem {
    override fun getSnippet() = ""

    override fun getTitle() = title

    override fun getPosition() = position
}