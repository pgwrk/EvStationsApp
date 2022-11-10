package com.pgsoft.evstationsapp.features.stations.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.data.model.StationsSettings
import com.pgsoft.evstationsapp.features.common.EvAppBar

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel = hiltViewModel(),
    onBackIconTapped: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()

    SettingsScreen(
        uiState.value,
        onBackIconTapped = onBackIconTapped,
        onShowDistanceTaped = {
            viewModel.setShowDistance(it)
        },
        onShowConnectorsTapped = {
            viewModel.setShowConnectors(it)
        }
    )
}

@Composable
fun SettingsScreen(
    settings: StationsSettings,
    onBackIconTapped: () -> Unit,
    onShowDistanceTaped: (Boolean) -> Unit,
    onShowConnectorsTapped: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        EvAppBar(
            titleId = R.string.settings_title,
            showBackIcon = true,
            onBackIconTapped = onBackIconTapped
        )

        Spacer(modifier = Modifier.height(24.dp))

        Setting(
            title = stringResource(id = R.string.settings_show_distance),
            isChecked = settings.showDistance,
            onCheckedChange = onShowDistanceTaped,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Setting(
            title = stringResource(id = R.string.settings_show_connectors),
            isChecked = settings.showConnectors,
            onCheckedChange = onShowConnectorsTapped,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun Setting(
    title: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.height(36.dp)) {
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier.align(Alignment.CenterVertically),
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary,
                checkedTrackColor = MaterialTheme.colors.primary
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            modifier = Modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground
        )
    }
}