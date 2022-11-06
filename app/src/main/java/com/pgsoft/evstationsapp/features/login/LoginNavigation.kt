package com.pgsoft.evstationsapp.features.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val loginRoute = "login_route"

fun NavController.navigateToLogin(navOptions: NavOptions) {
    this.navigate(loginRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(
    onLoggedIn: ()->Unit,
    onClose: ()->Unit
) {
    composable(route = loginRoute) {
        LoginRoute(onLoggedIn, onClose)
    }
}