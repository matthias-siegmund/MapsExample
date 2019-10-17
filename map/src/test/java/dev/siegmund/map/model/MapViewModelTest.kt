package dev.siegmund.map.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import dev.siegmund.map.api.ScooterRepository
import dev.siegmund.map.api.model.Scooter
import dev.siegmund.map.ui.model.Location
import dev.siegmund.map.ui.model.MapViewModel
import io.reactivex.Single
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertTrue

class MapViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var scooterObserver: Observer<List<Scooter>>

    @Mock
    lateinit var zoomOnClusterCenterObserver: Observer<List<Location>>

    @Mock
    lateinit var showErrorObserver: Observer<Unit>

    @Mock
    lateinit var scooterRepository: ScooterRepository

    private lateinit var mapViewModel: MapViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        whenever(scooterRepository.getScootersForPickup()).thenReturn(Single.just(scooters))
        mapViewModel = MapViewModel(
            scooterRepository,
            TestSchedulers.schedulers
        )
    }

    @Test
    fun `scooter list is fetched from repository when view model is initialized`() {
        // given
        whenever(scooterRepository.getScootersForPickup()).thenReturn(Single.just(scooters))

        // when
        mapViewModel.onStart()

        // then
        verify(scooterRepository, times(1)).getScootersForPickup()
    }

    @Test
    fun `map moved to cluster center when scooter list is fetched`() {
        // given
        whenever(scooterRepository.getScootersForPickup()).thenReturn(Single.just(scooters))
        mapViewModel.zoomOnClusterCenter.observeForever(zoomOnClusterCenterObserver)

        // when
        mapViewModel.onStart()

        // then
        val captor = argumentCaptor<List<Location>>()
        verify(zoomOnClusterCenterObserver, times(1)).onChanged(captor.capture())
        assertTrue { captor.firstValue == locations }
    }

    @Test
    fun `scooter list is updated when scooter list is fetched`() {
        // given
        whenever(scooterRepository.getScootersForPickup()).thenReturn(Single.just(scooters))
        mapViewModel.scooters.observeForever(scooterObserver)

        // when
        mapViewModel.onStart()

        // then
        val captor = argumentCaptor<List<Scooter>>()
        verify(scooterObserver, times(1)).onChanged(captor.capture())
        assertTrue { captor.firstValue == scooters }
    }

    @Test
    fun `scooter list is not updated when scooter list is fetched and error occurs`() {
        // given
        val error = Exception()
        whenever(scooterRepository.getScootersForPickup()).thenReturn(Single.error(error))
        mapViewModel.scooters.observeForever(scooterObserver)

        // when
        mapViewModel.onStart()

        // then
        val captor = argumentCaptor<List<Scooter>>()
        verify(scooterObserver, never()).onChanged(captor.capture())
    }

    @Test
    fun `map not moved to cluster center when scooter list is fetched and error occurs`() {
        // given
        val error = Exception()
        whenever(scooterRepository.getScootersForPickup()).thenReturn(Single.error(error))
        mapViewModel.zoomOnClusterCenter.observeForever(zoomOnClusterCenterObserver)

        // when
        mapViewModel.onStart()

        // then
        val captor = argumentCaptor<List<Location>>()
        verify(zoomOnClusterCenterObserver, never()).onChanged(captor.capture())
    }

    @Test
    fun `error is shown when scooter list is fetched and error occurs`() {
        // given
        val error = Exception()
        whenever(scooterRepository.getScootersForPickup()).thenReturn(Single.error(error))
        mapViewModel.showError.observeForever(showErrorObserver)

        // when
        mapViewModel.onStart()

        // then
        verify(showErrorObserver, times(1)).onChanged(Unit)
    }

    private companion object {
        val scooter1 = Scooter(
            id = "id-scooter1",
            vehicleId = "vehicleId-scooter1",
            hardwareId = "hardwareId-scooter1",
            zoneId = "zoneId-scooter1",
            resolution = null,
            resolvedBy = null,
            resolvedAt = null,
            battery = 50,
            state = "TO_PICKUP",
            model = "model",
            fleetbirdId = 1,
            latitude = 1.0,
            longitude = 2.0
        )
        val scooter2 = Scooter(
            id = "id-scooter2",
            vehicleId = "vehicleId-scooter2",
            hardwareId = "hardwareId-scooter2",
            zoneId = "zoneId-scooter2",
            resolution = null,
            resolvedBy = null,
            resolvedAt = null,
            battery = 50,
            state = "TO_PICKUP",
            model = "model",
            fleetbirdId = 1,
            latitude = 3.0,
            longitude = 4.0
        )
        val scooters = listOf(scooter1, scooter2)
        val locations = listOf(Location(1.0, 2.0), Location(3.0, 4.0))
    }
}
