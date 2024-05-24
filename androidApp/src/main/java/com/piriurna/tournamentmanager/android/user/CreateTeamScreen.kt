package com.piriurna.tournamentmanager.android.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun RegisterTeamScreen(
    viewModel: RegisterTeamViewModel,
    navController: NavController
) {
    val uiState = viewModel.uiState.value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = uiState.name, onValueChange = viewModel::onNameChange)
        TextField(value = uiState.imageUrl, onValueChange = viewModel::onImageUrlChange)

        Button(onClick = viewModel::registerTeam) {
            Text(text = "Register team")
        }
    }

}