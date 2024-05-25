package com.piriurna.tournamentmanager.android.dashboard.models

import com.piriurna.tournamentmanager.domain.models.Player
import com.piriurna.tournamentmanager.domain.models.Team

data class TeamUiData(
    val name: String,
    val image: String,
    val isOwner: Boolean,
    val players: List<Player>
)


fun Team.toUiData(): TeamUiData {
    return TeamUiData(
        name = name,
        image = "https://static.vecteezy.com/system/resources/previews/027/926/005/non_2x/football-logo-badge-with-a-soccer-ball-illustration-sport-team-logo-template-vector.jpg",
        isOwner = true,
        players = players
    )
}