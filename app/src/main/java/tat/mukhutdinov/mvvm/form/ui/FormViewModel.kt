package tat.mukhutdinov.mvvm.form.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import tat.mukhutdinov.mvvm.form.domain.model.Points

class FormViewModel(
    private val mediator: FormMediator
) : ViewModel() {

    private val _isSearchingRide = MutableStateFlow(false)
    val isSearchingRide: StateFlow<Boolean> = _isSearchingRide.asStateFlow()

    private val _selectedPoints = MutableStateFlow(Points())
    val selectedPoints: StateFlow<Points> = _selectedPoints.asStateFlow()

    fun updateSelectedPoints(points: (Points) -> Points) {
        _selectedPoints.update(points)
    }

    fun searchRide() {
        mediator.selectedPoints.value
        _isSearchingRide.update { true }


    }

    fun cancelRideSearch() {
        _isSearchingRide.update { false }
    }
}