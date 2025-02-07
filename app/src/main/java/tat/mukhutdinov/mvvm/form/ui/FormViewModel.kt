package tat.mukhutdinov.mvvm.form.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FormViewModel : ViewModel() {

    private val _isSearchingRide = MutableStateFlow(false)
    val isSearchingRide: StateFlow<Boolean> = _isSearchingRide.asStateFlow()

    fun searchRide() {
        _isSearchingRide.update { true }
    }

    fun cancelRideSearch() {
        _isSearchingRide.update { false }
    }
}