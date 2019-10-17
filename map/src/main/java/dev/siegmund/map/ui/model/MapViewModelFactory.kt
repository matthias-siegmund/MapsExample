package dev.siegmund.map.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.siegmund.core.rx.SchedulerConfiguration
import dev.siegmund.map.api.ScooterRepository

class MapViewModelFactory(
    private val scooterRepository: ScooterRepository,
    private val schedulerConfiguration: SchedulerConfiguration
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(
                scooterRepository,
                schedulerConfiguration
            ) as T
        }
        throw IllegalArgumentException()
    }
}