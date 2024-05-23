package com.piriurna.tournamentmanager.android.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.piriurna.tournamentmanager.android.dashboard.navigation.DashboardDestination
import com.piriurna.tournamentmanager.android.dashboard.navigation.dashboardNavigation
import com.piriurna.tournamentmanager.android.login.navigation.LoginRegisterDestination
import com.piriurna.tournamentmanager.android.login.navigation.loginNavigation
import com.piriurna.tournamentmanager.android.navigation.components.BottomNavigation
import com.piriurna.tournamentmanager.android.profile.navigation.profileNavigation

@Composable
fun AppNavGraph(
    auth: FirebaseAuth?,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val navController = rememberNavController()

    val backStackEntry = navController.currentBackStackEntryAsState()

    val currentDestination by remember {
        derivedStateOf {
            backStackEntry.value?.destination?.route
        }
    }

    var isUserLoggedIn by remember {
        mutableStateOf(false)
    }

    DisposableEffect(lifecycleOwner) {
        val authStateListener = AuthStateListener {
            isUserLoggedIn = it.currentUser != null
            if(it.currentUser == null) {
                navController.navigate(LoginRegisterDestination)
            } else {
                navController.navigate(DashboardDestination)
            }
        }

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                auth?.addAuthStateListener(authStateListener)
            } else if (event == Lifecycle.Event.ON_STOP) {
                auth?.removeAuthStateListener(authStateListener)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    Scaffold(
        bottomBar = {
            if(isUserLoggedIn)
                BottomNavigation(
                    items = listOf(BottomNavigationItemUi.Home, BottomNavigationItemUi.Profile),
                    onNavigate = { navController.navigate(it) },
                    currentDestination = currentDestination?:""
                )
        },
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = LoginRegisterDestination
        ) {
            loginNavigation(auth)

            dashboardNavigation { auth?.signOut() }

            profileNavigation(auth)
        }
    }

}