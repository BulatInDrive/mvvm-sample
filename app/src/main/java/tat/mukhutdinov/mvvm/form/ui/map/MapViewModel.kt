package tat.mukhutdinov.mvvm.form.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tat.mukhutdinov.mvvm.App
import tat.mukhutdinov.mvvm.form.domain.DestinationInteractor
import tat.mukhutdinov.mvvm.form.domain.FormAnalyticsInteractor
import tat.mukhutdinov.mvvm.form.ui.FormMediator
import tat.mukhutdinov.mvvm.form.domain.model.AvailableCars
import tat.mukhutdinov.mvvm.form.ui.map.delegate.CarsDelegate

class MapViewModel(
    private val mediator: FormMediator,
    private val destinationInteractor: DestinationInteractor,
    private val carsDelegate: CarsDelegate
) : ViewModel() {

    val availableCars: StateFlow<AvailableCars> = carsDelegate.availableCars

    private val _timeToDestinationMin = MutableStateFlow(0)
    val timeToDestinationMin: StateFlow<Int> = _timeToDestinationMin.asStateFlow()

    init {
        viewModelScope.launch {
            carsDelegate.fetchAvailableCars()

            mediator.selectedPoints.collectLatest { points ->
                _timeToDestinationMin.update {
                    destinationInteractor.calculateTimeToDestination(points)
                }
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MapViewModel(
                    mediator = (this[APPLICATION_KEY] as App).formMediator,
                    destinationInteractor = DestinationInteractor(),
                    carsDelegate = CarsDelegate(FormAnalyticsInteractor()),
                )
            }
        }
    }
}