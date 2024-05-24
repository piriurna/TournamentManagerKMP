package com.piriurna.tournamentmanager.android.dashboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.piriurna.tournamentmanager.android.dashboard.components.DashboardScreen

const val DashboardDestination = "dashboard_destination"

fun NavGraphBuilder.dashboardNavigation(navController: NavController) {

    composable(DashboardDestination) {
        DashboardScreen()
    }
}