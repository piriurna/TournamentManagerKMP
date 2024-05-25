package com.piriurna.tournamentmanager.android.tournament.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.piriurna.tournamentmanager.android.MyApplication
import com.piriurna.tournamentmanager.android.common.customViewModelFactory
import com.piriurna.tournamentmanager.android.tournament.components.TournamentScreen
import com.piriurna.tournamentmanager.android.tournament.components.TournamentsViewModel
import com.piriurna.tournamentmanager.android.tournament.navigation.TournamentDestinations.TournamentsOverviewDestination

fun NavGraphBuilder.tournamentsNavGraph(navController: NavController) {
    composable(TournamentsOverviewDestination) {
        val context = LocalContext.current
        TournamentScreen(viewModel = customViewModelFactory(navController = navController) {
            TournamentsViewModel(
                getTournamentsByDateUseCase =
                (context.applicationContext as MyApplication).getTournamentsByDateUseCase
            )
        })
    }
}