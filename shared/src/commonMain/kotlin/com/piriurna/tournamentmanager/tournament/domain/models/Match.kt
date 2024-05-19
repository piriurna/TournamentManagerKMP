package com.piriurna.tournamentmanager.tournament.domain.models

data class Match(
    val id: String,
    val homeTeam: MatchTeam,
    val awayTeam: MatchTeam,
    val winner: Team,
    val status: MatchStatus
) {

    enum class MatchStatus {
        LIVE, PAST, PRESENT
    }

    data class MatchTeam(
        val team: Team,
        val score: Int
    )
}
