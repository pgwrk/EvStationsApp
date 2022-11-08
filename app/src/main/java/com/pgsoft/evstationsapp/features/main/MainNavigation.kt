package com.pgsoft.evstationsapp.features.main

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val mainRoute = "main_route"

fun NavGraphBuilder.mainScreen(
    navigateToLogin : () -> Unit,
    navigateToStations : () -> Unit
) {
    composable(route = mainRoute) {
        val viewModel: MainNavigationViewModel= hiltViewModel()

        if (viewModel.hasToken()) {
            navigateToStations()
        } else {
            navigateToLogin()
        }
    }
}