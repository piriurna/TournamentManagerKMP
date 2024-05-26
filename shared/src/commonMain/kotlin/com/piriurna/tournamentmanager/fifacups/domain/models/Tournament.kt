package com.piriurna.tournamentmanager.fifacups.domain.models

import com.piriurna.tournamentmanager.fifacups.data.models.TournamentStatus
import kotlinx.datetime.LocalDateTime

data class Tournament(
    val id: String,
    val name: String,
    val status: TournamentStatus,
    val owner: User,
    val date: LocalDateTime,
    val teams: List<Team>,
    val groups: List<TournamentGroup>,
    val matches: List<Match>,
) {
}