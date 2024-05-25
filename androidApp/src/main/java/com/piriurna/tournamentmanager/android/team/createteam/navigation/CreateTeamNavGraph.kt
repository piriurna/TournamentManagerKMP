package com.piriurna.tournamentmanager.android.team.createteam.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.piriurna.tournamentmanager.android.MyApplication
import com.piriurna.tournamentmanager.android.common.customViewModelFactory
import com.piriurna.tournamentmanager.android.team.createteam.components.CreateTeamScreen
import com.piriurna.tournamentmanager.android.team.createteam.components.CreateTeamViewModel
import com.piriurna.tournamentmanager.android.team.createteam.navigation.CreateTeamNavigation.CreateTeamHomePageDestination

fun NavGraphBuilder.createTeamNavigation(navController: NavController) {
    composable(CreateTeamHomePageDestination) {
        val context = LocalContext.current
        CreateTeamScreen(viewModel = customViewModelFactory(navController = navController) {
            CreateTeamViewModel((context.applicationContext as MyApplication).createTeamUseCase)
        })
    }
}