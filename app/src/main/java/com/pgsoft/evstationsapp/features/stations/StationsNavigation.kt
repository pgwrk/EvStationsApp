package com.pgsoft.evstationsapp.features.stations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pgsoft.evstationsapp.features.stations.settings.SettingsRoute
import com.pgsoft.evstationsapp.features.stations.stationslist.StationsRoute

private const val stationsRoute = "stations_route"
private const val settingsRoute = "settings_route"

fun  NavController.navigateToStations(navOptions: NavOptions) {
    this.navigate(stationsRoute, navOptions)
}

fun  NavController.navigateToSettings() {
    this.navigate(settingsRoute)
}

fun NavGraphBuilder.stationsFeature(
    onShowSettings: () -> Unit,
    onCloseSettings: () -> Unit
) {
    composable(route = stationsRoute) {
        StationsRoute {
            onShowSettings()
        }
    }

    composable(route = settingsRoute) {
        SettingsRoute {
            onCloseSettings()
        }
    }
}