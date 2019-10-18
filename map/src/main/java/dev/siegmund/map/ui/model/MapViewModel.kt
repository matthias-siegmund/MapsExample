package dev.siegmund.map.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.siegmund.core.rx.SchedulerConfiguration
import dev.siegmund.core.rx.extensions.plus
import dev.siegmund.core.ui.SingleLiveEvent
import dev.siegmund.map.api.ScooterRepository
import dev.siegmund.map.api.model.Scooter
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class MapViewModel(
    private val scooterRepository: ScooterRepository,
    private val schedulerConfiguration: SchedulerConfiguration
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _scooters = MutableLiveData<List<Scooter>>()
    val scooters: LiveData<List<Scooter>>
        get() = _scooters

    private val _zoomOnClusterCenter = MutableLiveData<List<Location>>()
    val zoomOnClusterCenter: LiveData<List<Location>>
        get() = _zoomOnClusterCenter

    private val _showError = SingleLiveEvent<Unit>()
    val showError: LiveData<Unit>
        get() = _showError

    fun onStart() {
        getScootersForPickup()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun getScootersForPickup() {
        compositeDisposable + scooterRepository.getScootersForPickup()
            .subscribeOn(schedulerConfiguration.computation())
            .observeOn(schedulerConfiguration.ui())
            .subscribe({ scooters ->
                _scooters.value = scooters
                _zoomOnClusterCenter.value = scooters.map { Location(it.latitude, it.longitude) }
            }, { error ->
                Timber.e(error, "getScootersForPickup()")
                _showError.call()
            })
    }
}