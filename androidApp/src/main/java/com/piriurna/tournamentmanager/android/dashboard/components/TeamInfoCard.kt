package com.piriurna.tournamentmanager.android.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.piriurna.tournamentmanager.android.common.components.IconText
import com.piriurna.tournamentmanager.android.dashboard.models.TeamUiData

@Composable
fun TeamInfoCard(
    modifier: Modifier = Modifier,
    team: TeamUiData,
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
            Text(text = team.name)

            Spacer(modifier = Modifier.height(32.dp))

            TeamInfoRow(
                registeredPlayers = team.players.size,
                isOwner = team.isOwner
            )
        }
    }
}

@Composable
private fun TeamInfoRow(
    modifier: Modifier = Modifier,
    registeredPlayers: Int,
    isOwner: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconText(
            text = registeredPlayers.toString(),
            icon = Icons.Default.Face
        )

        if(isOwner)
            Text(text = "Owner")
    }
}