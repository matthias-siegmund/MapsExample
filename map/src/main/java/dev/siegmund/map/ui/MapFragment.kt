package dev.siegmund.map.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.model.LatLng
import dagger.android.support.AndroidSupportInjection
import dev.siegmund.map.R
import dev.siegmund.map.ui.model.MapViewModel
import dev.siegmund.map.ui.model.MapViewModelFactory
import kotlinx.android.synthetic.main.fragment_map.*
import javax.inject.Inject

class MapFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MapViewModelFactory

    private val viewModel: MapViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(MapViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)
        observeScooters()
        observeZoomOnClusterCenter()
        observeShowError()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        viewModel.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    private fun observeScooters() {
        viewModel.scooters.observe(this, Observer { scooters ->
            mapView.clearMarkers()
            mapView.addMarkers(scooters)
        })
    }

    private fun observeZoomOnClusterCenter() {
        viewModel.zoomOnClusterCenter.observe(this, Observer { locations ->
            mapView.zoomOnClusterCenter(locations.map { LatLng(it.latitude, it.longitude) })
        })
    }

    private fun observeShowError() {
        viewModel.showError.observe(this, Observer {
            Toast.makeText(requireContext(), R.string.error, Toast.LENGTH_LONG).show()
        })
    }
}