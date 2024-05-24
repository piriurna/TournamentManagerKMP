package com.piriurna.tournamentmanager.domain.models

data class TournamentGroup(
    val id: String,
    val tournament: Tournament,
    val matches: List<Match>,
    val teams: List<Team>
)