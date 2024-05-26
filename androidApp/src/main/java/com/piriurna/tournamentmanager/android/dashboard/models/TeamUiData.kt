package com.piriurna.tournamentmanager.android.dashboard.models

import com.piriurna.tournamentmanager.domain.models.Team
import com.piriurna.tournamentmanager.domain.models.User

data class TeamUiData(
    val name: String,
    val image: String,
    val isOwner: Boolean,
    val players: List<User>
)


fun Team.toUiData(loggedUserId: String?): TeamUiData {
    return TeamUiData(
        name = name,
        image = imageUrl,
        isOwner = owner.id == loggedUserId,
        players = players
    )
}