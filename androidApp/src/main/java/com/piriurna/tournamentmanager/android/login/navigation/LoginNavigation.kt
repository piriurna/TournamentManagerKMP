package com.piriurna.tournamentmanager.android.login.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.piriurna.tournamentmanager.android.MyApplication
import com.piriurna.tournamentmanager.android.common.customViewModelFactory
import com.piriurna.tournamentmanager.android.login.components.LoginScreen
import com.piriurna.tournamentmanager.android.login.components.LoginViewModel

const val LoginRegisterDestination = "LoginAndRegisterDestination"
fun NavGraphBuilder.loginNavigation(navController: NavController) {

    composable(LoginRegisterDestination) {
        val context = LocalContext.current
        LoginScreen(
            viewModel = customViewModelFactory(navController = navController) {
                LoginViewModel(
                    (context.applicationContext as MyApplication).createUserUseCase,
                )
            }
        )
    }
}