package dev.siegmund.map.ui.map

import android.content.Context
import android.util.AttributeSet
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import dev.siegmund.map.api.model.Scooter
import com.google.maps.android.clustering.ClusterManager
import dev.siegmund.map.ui.map.cluster.MarkerClusterItem
import dev.siegmund.map.ui.map.cluster.MarkerClusterRenderer
import com.google.android.gms.maps.model.LatLngBounds

class MapView(context: Context, attrs: AttributeSet?) : MapView(context, attrs) {
    private lateinit var clusterManager: ClusterManager<MarkerClusterItem>

    init {
        getMapAsync { map ->
            initClustering(map)
            map.uiSettings.apply {
                isMyLocationButtonEnabled = false
                isCompassEnabled = false
                isMapToolbarEnabled = false
                isRotateGesturesEnabled = false
                isIndoorLevelPickerEnabled = false
            }
        }
    }

    fun zoomOnClusterCenter(locations: List<LatLng>) = getMapAsync { map ->
        if (locations.isNotEmpty()) {
            val padding = 100
            map.animateCamera(
                CameraUpdateFactory.newLatLngBounds(
                    getBoundsForLocations(locations),
                    padding
                )
            )
        }
    }

    fun clearMarkers() {
        clusterManager.clearItems()
    }

    fun addMarkers(scooters: List<Scooter>) {
        scooters.forEach { scooter ->
            val position = LatLng(scooter.latitude, scooter.longitude)
            clusterManager.addItem(
                MarkerClusterItem(
                    scooter,
                    position,
                    scooter.id
                )
            )
        }
    }

    private fun initClustering(map: GoogleMap) {
        clusterManager = ClusterManager(context, map)
        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)
        map.setOnInfoWindowClickListener(clusterManager)
        map.setInfoWindowAdapter(clusterManager.markerManager)
        clusterManager.renderer = MarkerClusterRenderer(context, map, clusterManager)
        clusterManager.markerCollection.setOnInfoWindowAdapter(InfoWindowAdapter(context))
        clusterManager.cluster()
    }

    private fun getBoundsForLocations(locations: List<LatLng>): LatLngBounds {
        val builder = LatLngBounds.Builder()
        locations.forEach { builder.include(it) }
        return builder.build()
    }
}