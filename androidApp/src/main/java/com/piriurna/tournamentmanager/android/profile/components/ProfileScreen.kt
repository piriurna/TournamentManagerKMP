package com.piriurna.tournamentmanager.android.profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.piriurna.tournamentmanager.android.R

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { viewModel.logout() }) {
            Text(text = stringResource(R.string.logout))
        }
    }
}