package dev.siegmund.map.ui.map

import android.content.Context
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import dev.siegmund.map.R
import dev.siegmund.map.ui.map.extensions.scooter
import kotlinx.android.synthetic.main.info_window.view.*


class InfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(p0: Marker?) = null

    override fun getInfoWindow(marker: Marker): View {
        val view = View.inflate(context, R.layout.info_window, null)
        val scooter = marker.scooter()
        view.idDescriptionTextView.text = scooter.id
        view.batteryDescriptionTextView.text = context.getString(
            R.string.info_window_battery_description,
            scooter.battery
        )
        return view
    }
}