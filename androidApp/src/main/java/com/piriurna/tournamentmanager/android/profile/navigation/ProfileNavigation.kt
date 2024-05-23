package com.piriurna.tournamentmanager.android.profile.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.piriurna.tournamentmanager.android.R

const val ProfileDestination = "account_destination"


fun NavGraphBuilder.profileNavigation(auth: FirebaseAuth?) {
    composable(ProfileDestination) {
        Box(modifier = Modifier.fillMaxSize()) {
            Button(onClick = { auth?.signOut() }) {
                Text(text = stringResource(R.string.logout))
            }
        }
    }
}