package com.piriurna.tournamentmanager.domain.models

import com.piriurna.tournamentmanager.data.models.TournamentStatus
import kotlinx.datetime.LocalDateTime

data class Tournament(
    val id: String,
    val name: String,
    val owner: Player,
    val teams: List<Team>,
    val matches: List<Match>,
    val date: LocalDateTime,
    val status: TournamentStatus
) {
}