package dev.siegmund.map.ui.map.cluster

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class MarkerClusterRenderer(
    context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<MarkerClusterItem>
) : DefaultClusterRenderer<MarkerClusterItem>(context, map, clusterManager) {
    override fun onClusterItemRendered(clusterItem: MarkerClusterItem, marker: Marker) {
        super.onClusterItemRendered(clusterItem, marker)
        marker.tag = clusterItem.scooter
    }
}