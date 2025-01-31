package tat.mukhutdinov.mvvm.form.ui.map.delegate

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import tat.mukhutdinov.mvvm.form.domain.FormAnalyticsInteractor
import tat.mukhutdinov.mvvm.form.domain.model.AvailableCars

class CarsDelegate(
    private val formAnalyticsInteractor: FormAnalyticsInteractor,
) {

    private val _availableCars = MutableStateFlow(AvailableCars())
    val availableCars: StateFlow<AvailableCars> = _availableCars.asStateFlow()

    suspend fun fetchAvailableCars() {
        _availableCars.update {
            AvailableCars(
                economy = 100,
                business = 30,
                sixSeated = 8
            ).also {
                formAnalyticsInteractor.trackAvailableCars(it)
            }
        }
    }
}