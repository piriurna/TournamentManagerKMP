package com.piriurna.tournamentmanager.domain.models

data class TournamentGroup(
    val id: String,
    val tournament: Tournament,
    val matches: List<Match>,
    val table: List<GroupTeamInfo>
) {
    data class GroupTeamInfo(
        val team: Team,
        val points: Int,
        val goals: Int,
        val goalsTaken: Int,
        val matchesPlayed: Int
    )
}