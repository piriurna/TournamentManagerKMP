package com.piriurna.tournamentmanager.tournament.domain.models

data class TournamentGroup(
    val id: String,
    val tournament: Tournament,
    val matches: List<Match>,
    val teams: List<Team>
)