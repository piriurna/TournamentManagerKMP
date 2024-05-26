package com.piriurna.tournamentmanager.fifacups.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiTournamentGroup(
    val id: String,
    val table: List<ApiTeamGroupInfo>,
    val groupMatches: List<ApiMatch>
) {
    @Serializable
    data class ApiTeamGroupInfo(
        val team: ApiTeam,
        val playedMatches: Int,
        val points: Int,
        val goals: Int,
        val goalsTaken: Int
    )
}
