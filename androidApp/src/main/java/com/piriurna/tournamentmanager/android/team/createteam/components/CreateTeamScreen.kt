package com.piriurna.tournamentmanager.android.team.createteam.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.piriurna.tournamentmanager.android.R


@Composable
fun CreateTeamScreen(
    viewModel: CreateTeamViewModel
) {
    val uiState = viewModel.uiState.value
    Column {
        TextField(value = uiState.teamName, onValueChange = viewModel::updateTeamName)
        TextField(value = uiState.teamImage, onValueChange = viewModel::updateImageUrl)
        Button(onClick = viewModel::createTeam) {
            Text(stringResource(R.string.create_team))
        }

        if(uiState.error != null)
            Text(text = uiState.error, style = MaterialTheme.typography.bodySmall, color = Color.Red)
    }
}