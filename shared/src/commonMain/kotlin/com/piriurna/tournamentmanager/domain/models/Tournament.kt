package com.piriurna.tournamentmanager.domain.models

data class Tournament(
    val id: String,
    val name: String,
    val owner: Player,
    val groups: List<TournamentGroup>,
    val matches: List<Match>
) {
    val teams : List<Team> = groups.flatMap { it.teams }
}