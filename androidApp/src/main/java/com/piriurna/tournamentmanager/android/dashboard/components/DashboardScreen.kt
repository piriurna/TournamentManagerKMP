package com.piriurna.tournamentmanager.android.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.piriurna.tournamentmanager.android.R
import com.piriurna.tournamentmanager.android.dashboard.models.toUiData

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel
) {
    val uiState = viewModel.uiState.value
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        if(uiState.myTeam != null) {
            Text(text = stringResource(R.string.my_team))
            Spacer(modifier = Modifier.height(8.dp))
            TeamInfoCard(
                team = uiState.myTeam.toUiData(uiState.loggedInUser?.id),
                verticalSpacing = 12.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if(uiState.nextTournament != null) {
            Text(text = stringResource(R.string.next_tournament))
            Spacer(modifier = Modifier.height(8.dp))
            TournamentInfoCard(
                tournament = uiState.nextTournament.toUiData(),
                onAction = {  },
                verticalSpacing = 12.dp
            )
        }
        Button(onClick = viewModel::goToCreateTeamPage) {
            Text(text = stringResource(R.string.create_a_new_team))
        }
    }
}