package tat.mukhutdinov.mvvm.form.ui.abPoints

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun AbPoints(
    modifier: Modifier = Modifier,
    abPointViewModel: AbPointViewModel = viewModel(factory = AbPointViewModel.Factory),
    formViewModel: FormViewModel = viewModel(),
) {
    val points by abPointViewModel.points.collectAsStateWithLifecycle()
    val price by abPointViewModel.price.collectAsStateWithLifecycle()
    val isSearchingRide by formViewModel.isSearchingRide.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
    ) {
        Text(
            "Рекомендованная цена - $price рублей"
        )

        TextField(
            modifier = Modifier.padding(top = 16.dp),
            value = points.a,
            onValueChange = { abPointViewModel.onPointAChanged(it) },
            label = { Text("Откуда") }
        )

        TextField(
            modifier = Modifier.padding(bottom = 16.dp),
            value = points.b,
            onValueChange = { abPointViewModel.onPointBChanged(it) },
            label = { Text("Куда") }
        )

        if (isSearchingRide) {
            Button(
                onClick = { formViewModel.cancelRideSearch() }
            ) {
                Text("Отменить поиск")
            }
        } else {
            Button(
                onClick = { formViewModel.searchRide() }
            ) {
                Text("Найти поездку")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MvvmTheme {
        AbPoints()
    }
}