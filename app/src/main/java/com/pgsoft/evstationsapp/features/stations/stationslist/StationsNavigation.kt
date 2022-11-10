package com.pgsoft.evstationsapp.features.stations.stationslist

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

private const val stationsRoute = "stations_route"

fun  NavController.navigateToStations(navOptions: NavOptions) {
    this.navigate(stationsRoute, navOptions)
}

fun NavGraphBuilder.stationsScreen() {
    composable(route = stationsRoute) {
        StationsRoute()
    }
}