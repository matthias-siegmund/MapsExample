package dev.siegmund.map.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MapFragment : SupportMapFragment(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory: MapViewModelFactory

    private val viewModel: MapViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(MapViewModel::class.java)
    }

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync(this)
        observeScooters()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    private fun observeScooters() {
        viewModel.scooters.observe(this, Observer { scooters ->
            getMapAsync { asyncMap ->
                scooters.forEach { scooter ->
                    asyncMap.addMarker(
                        MarkerOptions().position(LatLng(scooter.latitude, scooter.longitude))
                    )
                }
            }
        })
    }
}