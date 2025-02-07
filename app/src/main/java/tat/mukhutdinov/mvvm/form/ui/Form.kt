package tat.mukhutdinov.mvvm.form.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tat.mukhutdinov.mvvm.form.ui.abPoints.AbPoints
import tat.mukhutdinov.mvvm.form.ui.map.Map
import tat.mukhutdinov.mvvm.core.theme.MvvmTheme


@Composable
fun Form(
    modifier: Modifier = Modifier,
    formViewModel: FormViewModel = viewModel(factory = FormViewModel.Factory),
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Map(modifier = Modifier.padding(top = 16.dp))

        AbPoints(modifier = Modifier.padding(top = 64.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    MvvmTheme {
        Form()
    }
}