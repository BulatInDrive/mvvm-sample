package tat.mukhutdinov.mvvm.form.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import tat.mukhutdinov.mvvm.form.domain.model.Points

class FormMediator {

    private val _selectedPoints = MutableStateFlow(Points())
    val selectedPoints: StateFlow<Points> = _selectedPoints.asStateFlow()

    fun updateSelectedPoints(points: (Points) -> Points) {
        _selectedPoints.update(points)
    }
}