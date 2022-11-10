package com.pgsoft.evstationsapp.features.stations.stationslist

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.features.common.EvAppBar
import com.pgsoft.evstationsapp.features.stations.stationslist.view.ContentScreen
import com.pgsoft.evstationsapp.features.stations.stationslist.view.ErrorScreen
import com.pgsoft.evstationsapp.features.stations.stationslist.view.LoadingScreen
import com.pgsoft.evstationsapp.ui.theme.EvStationsAppTheme

@Composable
fun StationsRoute(
    viewModel: StationsViewModel = hiltViewModel(),
    onSettings: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    StationsScreen(
        uiState = uiState,
        onRetry = { viewModel.load() },
        onSettings = onSettings
    )
}

@Composable
fun StationsScreen(
    uiState: StationsUiState,
    onRetry: () -> Unit,
    onSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        EvAppBar(
            titleId = R.string.stations_title,
            iconId = R.drawable.ic_settings,
            onIconTapped = onSettings
        )

        when (uiState) {
            StationsUiState.Loading -> LoadingScreen()
            is StationsUiState.Error ->
                ErrorScreen(
                    description = uiState.description,
                    onRetry = onRetry
                )
            is StationsUiState.Content ->
                ContentScreen(
                    stations = uiState.stations,
                    onRetry = onRetry
                )
        }
    }
}

@Preview(widthDp = 360, heightDp = 720)
@Preview(device = Devices.PIXEL_3, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StationsScreenPreview() = EvStationsAppTheme {
}