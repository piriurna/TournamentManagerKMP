package com.piriurna.tournamentmanager.android.dashboard.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.piriurna.tournamentmanager.android.MyApplication
import com.piriurna.tournamentmanager.android.common.customViewModelFactory
import com.piriurna.tournamentmanager.android.dashboard.components.DashboardScreen
import com.piriurna.tournamentmanager.android.dashboard.components.DashboardViewModel

const val DashboardDestination = "dashboard_destination"

fun NavGraphBuilder.dashboardNavigation(navController: NavController) {

    composable(DashboardDestination) {
        val context = LocalContext.current
        DashboardScreen(viewModel = customViewModelFactory(navController = navController) {
            DashboardViewModel(
                (context.applicationContext as MyApplication).getNextTournamentForUserUseCase,
                (context.applicationContext as MyApplication).getUserTeamUseCase,
                (context.applicationContext as MyApplication).getLoggedInUserUseCase,
            )
        })
    }
}