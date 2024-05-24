package com.piriurna.tournamentmanager.android.login.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.piriurna.tournamentmanager.android.MyApplication
import com.piriurna.tournamentmanager.android.common.customViewModelFactory
import com.piriurna.tournamentmanager.android.login.components.LoginPage
import com.piriurna.tournamentmanager.android.login.components.LoginViewModel
import dev.gitlive.firebase.auth.FirebaseUser

const val LoginRegisterDestination = "LoginAndRegisterDestination"
fun NavGraphBuilder.loginNavigation(navController: NavController, onAuthSuccess: (FirebaseUser?) -> Unit) {

    composable(LoginRegisterDestination) {
        val context = LocalContext.current
        LoginPage(
            viewModel = customViewModelFactory(navController = navController) {
                LoginViewModel(
                    (context.applicationContext as MyApplication).createUserUseCase,
                )
            }
        )
    }
}