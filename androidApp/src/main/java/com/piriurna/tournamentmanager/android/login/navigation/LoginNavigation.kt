package com.piriurna.tournamentmanager.android.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.piriurna.tournamentmanager.android.login.components.LoginPage
import com.piriurna.tournamentmanager.android.login.components.LoginViewModel

const val LoginRegisterDestination = "LoginAndRegisterDestination"
fun NavGraphBuilder.loginNavigation(firebaseAuth: FirebaseAuth?) {

    composable(LoginRegisterDestination) {
        LoginPage(viewModel = LoginViewModel(firebaseAuth))
    }
}