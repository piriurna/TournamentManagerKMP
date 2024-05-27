package com.piriurna.tournamentmanager.android.tournament.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.piriurna.tournamentmanager.android.common.components.IconText
import com.piriurna.tournamentmanager.android.tournament.models.TournamentInfoUiData
import com.piriurna.tournamentmanager.fifacups.domain.models.Match

@Composable
fun TournamentInfoCard(
    modifier: Modifier = Modifier,
    tournament: TournamentInfoUiData,
    onAction: () -> Unit,
    verticalSpacing: Dp = 0.dp
) {
    ElevatedCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(verticalSpacing)
        ) {
            Text(text = tournament.name)
            
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = onAction,
                shape = RoundedCornerShape(8.dp),

            ) {
                Text(text = tournament.buttonText)
            }

            TournamentInfoRow(
                registeredPlayers = tournament.registeredTeams.size,
                tournamentStatus = tournament.statusText
            )
        }
    }
}

@Composable
private fun TournamentInfoRow(
    modifier: Modifier = Modifier,
    registeredPlayers: Int,
    tournamentStatus: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconText(
            text = registeredPlayers.toString(),
            icon = Icons.Default.Face
        )

        IconText(
            text = tournamentStatus,
            icon = Icons.Default.DateRange
        )
    }
}

@Preview
@Composable
private fun TournamentInfoCardPreview() {
    TournamentInfoCard(
        tournament = TournamentInfoUiData(
            name = "Tournament",
            tournamentDate = "04-05-2000",
            status = Match.MatchStatus.LIVE,
            registeredTeams = emptyList()
        ),
        onAction = {},
        verticalSpacing = 24.dp
    )
}
