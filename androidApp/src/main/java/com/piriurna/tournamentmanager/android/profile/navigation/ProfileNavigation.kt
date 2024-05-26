package com.piriurna.tournamentmanager.android.profile.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.piriurna.tournamentmanager.android.MyApplication
import com.piriurna.tournamentmanager.android.common.customViewModelFactory
import com.piriurna.tournamentmanager.android.profile.components.ProfileScreen
import com.piriurna.tournamentmanager.android.profile.components.ProfileViewModel

const val ProfileDestination = "account_destination"


fun NavGraphBuilder.profileNavigation(navController: NavController) {
    composable(ProfileDestination) {
        val context = LocalContext.current
        ProfileScreen(
            viewModel = customViewModelFactory(navController = navController) {
                ProfileViewModel(
                    (context.applicationContext as MyApplication).firebaseApi
                )
            }
        )
    }
}