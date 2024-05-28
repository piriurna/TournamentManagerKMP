package com.piriurna.tournamentmanager.android.navigation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.piriurna.tournamentmanager.android.AppUiState
import com.piriurna.tournamentmanager.android.common.components.LoadingScreen
import com.piriurna.tournamentmanager.android.dashboard.navigation.DashboardDestination
import com.piriurna.tournamentmanager.android.dashboard.navigation.dashboardNavigation
import com.piriurna.tournamentmanager.android.login.navigation.LoginDestinations.LoginDestination
import com.piriurna.tournamentmanager.android.login.navigation.loginNavigation
import com.piriurna.tournamentmanager.android.navigation.BottomNavigationItemUi
import com.piriurna.tournamentmanager.android.profile.navigation.profileNavigation
import com.piriurna.tournamentmanager.android.team.navigation.teamNavigation
import com.piriurna.tournamentmanager.android.tournament.navigation.tournamentsNavGraph

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    appUiState: AppUiState,
    destinationsWithBottomSheet: List<BottomNavigationItemUi> = listOf(BottomNavigationItemUi.Home, BottomNavigationItemUi.Tournaments, BottomNavigationItemUi.Profile),

    ) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    val currentDestination by remember {
        derivedStateOf {
            backStackEntry.value?.destination?.route
        }
    }

    LaunchedEffect(key1 = appUiState.loggedInUser) {
        if(appUiState.loggedInUser == null)
            navController.navigate(LoginDestination)
        else
            navController.navigate(DashboardDestination)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(currentDestination in destinationsWithBottomSheet.map { it.destination }) {
                appUiState.loggedInUser?.let {
                    BottomNavigation(
                        items = destinationsWithBottomSheet,
                        onNavigate = { navController.navigate(it) },
                        currentDestination = currentDestination?:""
                    )
                }
            }
        },
    ) { paddingValues ->
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                navController = navController,
                startDestination = DashboardDestination
            ) {
                loginNavigation(navController)

                dashboardNavigation(navController)

                teamNavigation(navController)

                profileNavigation(navController)

                tournamentsNavGraph(navController)
            }

        if(appUiState.isLoading)
            LoadingScreen()
    }
}