package com.piriurna.tournamentmanager.android.navigation

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
import com.piriurna.tournamentmanager.android.createteam.navigation.createTeamNavigation
import com.piriurna.tournamentmanager.android.dashboard.navigation.DashboardDestination
import com.piriurna.tournamentmanager.android.dashboard.navigation.dashboardNavigation
import com.piriurna.tournamentmanager.android.login.navigation.LoginRegisterDestination
import com.piriurna.tournamentmanager.android.login.navigation.loginNavigation
import com.piriurna.tournamentmanager.android.navigation.components.BottomNavigation
import com.piriurna.tournamentmanager.android.profile.navigation.profileNavigation
import dev.gitlive.firebase.auth.FirebaseUser

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    appUiState: AppUiState,
    updateLoggedInUser: (FirebaseUser?) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    val currentDestination by remember {
        derivedStateOf {
            backStackEntry.value?.destination?.route
        }
    }

    LaunchedEffect(key1 = appUiState.loggedInUser) {
        if(appUiState.loggedInUser == null)
            navController.navigate(LoginRegisterDestination)
        else
            navController.navigate(DashboardDestination)
    }

    Scaffold(
        bottomBar = {
            appUiState.loggedInUser?.let {
                BottomNavigation(
                    items = listOf(BottomNavigationItemUi.Home, BottomNavigationItemUi.Profile),
                    onNavigate = { navController.navigate(it) },
                    currentDestination = currentDestination?:""
                )
            }
        },
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = DashboardDestination
        ) {
            loginNavigation(navController) { updateLoggedInUser(it) }

            dashboardNavigation(navController)

            createTeamNavigation(navController)

            profileNavigation(navController)
        }
    }

}