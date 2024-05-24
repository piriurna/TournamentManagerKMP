package com.piriurna.tournamentmanager.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiMatch(
    val id: String,
    val homeApiTeam: ApiTeam,
    val awayApiTeam: ApiTeam,
    val winnerId: String,
    val homeGoals: Int,
    val awayGoals: Int,
    val status: MatchStatus
) {

    enum class MatchStatus {
        LIVE, PAST, PRESENT
    }
}
