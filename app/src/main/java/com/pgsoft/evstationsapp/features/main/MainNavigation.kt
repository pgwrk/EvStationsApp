package com.pgsoft.evstationsapp.features.main

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val mainRoute = "main_route"

fun NavGraphBuilder.mainScreen(
    navigateToLogin : () -> Unit,
    navigateToStations : () -> Unit,
) {
    composable(route = mainRoute) {
        LaunchedEffect(true) {
            if (isAuthenticated()) {
                navigateToStations()
            } else {
                navigateToLogin()
            }
        }
    }
}

private suspend fun isAuthenticated(): Boolean = false