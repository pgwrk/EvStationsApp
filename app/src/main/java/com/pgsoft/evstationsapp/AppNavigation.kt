package com.pgsoft.evstationsapp

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.pgsoft.evstationsapp.features.login.loginRoute
import com.pgsoft.evstationsapp.features.login.loginScreen
import com.pgsoft.evstationsapp.features.login.navigateToLogin
import com.pgsoft.evstationsapp.features.main.mainRoute
import com.pgsoft.evstationsapp.features.main.mainScreen
import com.pgsoft.evstationsapp.features.stations.navigateToSettings
import com.pgsoft.evstationsapp.features.stations.navigateToStations
import com.pgsoft.evstationsapp.features.stations.stationsFeature

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = mainRoute) {
        mainScreen(
            navigateToLogin = {
                navController.navigateToLogin(navPopUpOptions(mainRoute))
            },
            navigateToStations = {
                navController.navigateToStations(navPopUpOptions(mainRoute))
            }
        )

        loginScreen(
            onLoggedIn = {
                navController.navigateToStations(navPopUpOptions(loginRoute))
            },
            onClose = {
                (navController.context as Activity).finish()
            }
        )

        stationsFeature(
            onShowSettings = {
                navController.navigateToSettings()
            },
            onCloseSettings = {
                navController.popBackStack()
            }
        )
    }
}

private fun navPopUpOptions(route: String) = navOptions {
    popUpTo(route) { inclusive = true }
}