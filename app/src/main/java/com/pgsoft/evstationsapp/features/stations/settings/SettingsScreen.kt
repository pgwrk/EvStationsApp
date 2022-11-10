package com.pgsoft.evstationsapp.features.stations.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.features.common.EvAppBar

@Composable
fun SettingsRoute(
    onBackIconTapped: () -> Unit
) {
    SettingsScreen(onBackIconTapped = onBackIconTapped)
}

@Composable
fun SettingsScreen(onBackIconTapped: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        EvAppBar(
            titleId = R.string.settings_title,
            showBackIcon = true,
            onBackIconTapped = onBackIconTapped
        )
    }
}