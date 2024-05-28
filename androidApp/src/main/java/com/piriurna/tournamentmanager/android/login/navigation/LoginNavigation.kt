package com.piriurna.tournamentmanager.android.login.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.piriurna.tournamentmanager.android.MyApplication
import com.piriurna.tournamentmanager.android.common.customViewModelFactory
import com.piriurna.tournamentmanager.android.login.components.login.LoginScreen
import com.piriurna.tournamentmanager.android.login.components.login.LoginViewModel
import com.piriurna.tournamentmanager.android.login.components.register.RegisterScreen
import com.piriurna.tournamentmanager.android.login.components.register.RegisterViewModel
import com.piriurna.tournamentmanager.android.login.navigation.LoginDestinations.LoginDestination
import com.piriurna.tournamentmanager.android.login.navigation.LoginDestinations.RegisterDestination

fun NavGraphBuilder.loginNavigation(navController: NavController) {

    composable(RegisterDestination) {
        val context = LocalContext.current
        RegisterScreen(
            viewModel = customViewModelFactory(navController = navController) {
                RegisterViewModel(
                    (context.applicationContext as MyApplication).createUserUseCase,
                )
            }
        )
    }
    composable(LoginDestination) {
        val context = LocalContext.current
        LoginScreen(
            viewModel = customViewModelFactory(navController = navController) {
                LoginViewModel(
                    (context.applicationContext as MyApplication).loginUserUseCase,
                    (context.applicationContext as MyApplication).getLoggedInUserUseCase,

                )
            }
        )
    }
}