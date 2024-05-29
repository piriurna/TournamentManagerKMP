package com.piriurna.tournamentmanager.android.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.tournamentmanager.android.R
import com.piriurna.tournamentmanager.android.common.components.ButtonWithIconAndText
import com.piriurna.tournamentmanager.android.common.components.LoadingScreen
import com.piriurna.tournamentmanager.android.team.components.TeamInfoCard
import com.piriurna.tournamentmanager.android.team.models.toUiData
import com.piriurna.tournamentmanager.android.tournament.components.TournamentInfoCard
import com.piriurna.tournamentmanager.android.tournament.models.toUiData

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel
) {
    val uiState = viewModel.uiState.value

    when {
        uiState.isLoading -> {
            LoadingScreen()
        }

        else -> {
            DashboardScreenContent(
                modifier = modifier,
                uiState = uiState,
                goToCreateTeamPage = viewModel::goToCreateTeamPage,
                goToCreateTournamentPage = viewModel::goToCreateTournamentPage
            )
        }
    }
}

@Composable
private fun DashboardScreenContent(
    modifier: Modifier = Modifier,
    uiState: DashboardUiState,
    goToCreateTeamPage: () -> Unit,
    goToCreateTournamentPage: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    MaterialTheme.typography.headlineLarge.toSpanStyle()
                        .copy(color = MaterialTheme.colorScheme.primary),
                ) {
                    append(stringResource(R.string.welcome))
                    append(", ")
                }

                withStyle(MaterialTheme.typography.headlineLarge.toSpanStyle()) {
                    if(uiState.loggedInUser != null) append(uiState.loggedInUser.nickname)
                }
            },
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        if(uiState.myTeam != null) {
            Text(text = stringResource(R.string.my_team))
            Spacer(modifier = Modifier.height(8.dp))
            TeamInfoCard(
                team = uiState.myTeam.toUiData(uiState.loggedInUser?.id),
                verticalSpacing = 12.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
        } else {
            ButtonWithIconAndText(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = goToCreateTeamPage,
                icon = painterResource(id = R.drawable.ic_team),
                text = stringResource(id = R.string.create_a_new_team)
            )
        }

        if(uiState.nextTournament != null) {
            Text(text = stringResource(R.string.next_tournament))
            Spacer(modifier = Modifier.height(8.dp))
            TournamentInfoCard(
                tournament = uiState.nextTournament.toUiData(),
                onAction = {  },
                verticalSpacing = 12.dp
            )
        } else {
            ButtonWithIconAndText(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = goToCreateTournamentPage,
                icon = painterResource(id = R.drawable.ic_tournament),
                text = stringResource(id = R.string.create_new_tournament)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DashboardScreenPreview() {
    DashboardScreenContent(
        uiState = DashboardUiState(),
        goToCreateTeamPage = { /*TODO*/ },
        goToCreateTournamentPage = {  }
    )
}