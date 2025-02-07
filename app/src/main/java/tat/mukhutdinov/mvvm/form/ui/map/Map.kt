package tat.mukhutdinov.mvvm.form.ui.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import tat.mukhutdinov.mvvm.core.theme.MvvmTheme
import tat.mukhutdinov.mvvm.form.ui.FormViewModel

@Composable
fun Map(
    modifier: Modifier = Modifier,
    mapViewModel: MapViewModel = viewModel(factory = MapViewModel.Factory),
    formViewModel: FormViewModel = viewModel(factory = FormViewModel.Factory),
) {
    val isSearchingRide by formViewModel.isSearchingRide.collectAsStateWithLifecycle()
    val availableCars by mapViewModel.availableCars.collectAsStateWithLifecycle()
    val timeToDestinationMin by mapViewModel.timeToDestinationMin.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
    ) {

        if (isSearchingRide) {
            Text(
                "Подбираем вам водителя"
            )
        } else {
            Text(
                "Выберите нужные точки и нажмите Поиск"
            )
        }

        Text(
            modifier = modifier.padding(top = 16.dp),
            text = "Доступно ${availableCars.economy} эконом машин и ${availableCars.business} машин бизнес класса"
        )

        if (timeToDestinationMin > 0) {
            Text(
                modifier = modifier.padding(top = 16.dp),
                text = "Время в пути составит $timeToDestinationMin минут"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MvvmTheme {
        Map()
    }
}