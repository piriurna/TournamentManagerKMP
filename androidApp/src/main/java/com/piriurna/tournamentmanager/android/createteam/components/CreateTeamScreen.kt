package com.piriurna.tournamentmanager.android.createteam.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun CreateTeamScreen(
    viewModel: CreateTeamViewModel
) {
    val uiState = viewModel.uiState.value
    Column {
        TextField(value = uiState.teamName, onValueChange = viewModel::updateTeamName)
        TextField(value = uiState.teamImage, onValueChange = viewModel::updateImageUrl)
        Button(onClick = viewModel::createTeam) {
            Text("Create Team")
        }

        if(uiState.error != null)
            Text(text = uiState.error, style = MaterialTheme.typography.bodySmall, color = Color.Red)
    }
}