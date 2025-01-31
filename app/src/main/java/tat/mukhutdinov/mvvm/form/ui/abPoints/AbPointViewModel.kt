package tat.mukhutdinov.mvvm.form.ui.abPoints

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import tat.mukhutdinov.mvvm.App
import tat.mukhutdinov.mvvm.form.ui.FormMediator
import tat.mukhutdinov.mvvm.form.domain.model.Points
import kotlin.math.absoluteValue

class AbPointViewModel(
    private val mediator: FormMediator
) : ViewModel() {

    val points: StateFlow<Points> = mediator.selectedPoints

    private val _price = MutableStateFlow(0)
    val price: StateFlow<Int> = _price.asStateFlow()

    fun onPointAChanged(value: String) {
        mediator.updateSelectedPoints { old ->
            old.copy(a = value).also { new ->
                onPointsUpdated(new)
            }
        }
    }

    fun onPointBChanged(value: String) {
        mediator.updateSelectedPoints { old ->
            old.copy(b = value).also { new ->
                onPointsUpdated(new)
            }
        }
    }

    private fun onPointsUpdated(points: Points) {
        _price.update {
            ((points.a + points.b).hashCode() % 1000).absoluteValue
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AbPointViewModel((this[APPLICATION_KEY] as App).formMediator)
            }
        }
    }
}