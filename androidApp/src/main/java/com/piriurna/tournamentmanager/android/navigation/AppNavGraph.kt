package com.piriurna.tournamentmanager.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.piriurna.tournamentmanager.android.AppState
import com.piriurna.tournamentmanager.android.dashboard.navigation.DashboardDestination
import com.piriurna.tournamentmanager.android.dashboard.navigation.dashboardNavigation
import com.piriurna.tournamentmanager.android.login.navigation.LoginRegisterDestination
import com.piriurna.tournamentmanager.android.login.navigation.loginNavigation

@Composable
fun AppNavGraph(
    appState: AppState,
    auth: FirebaseAuth?,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val navController = rememberNavController()


    DisposableEffect(lifecycleOwner) {
        val authStateListener = AuthStateListener {
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
    NavHost(
        navController = navController,
        startDestination = LoginRegisterDestination
    ) {
        loginNavigation(auth)

        dashboardNavigation { auth?.signOut() }
    }

}