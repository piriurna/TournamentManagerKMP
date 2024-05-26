package com.piriurna.tournamentmanager.data.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ApiTournament(
    val id: String,
    val name: String,
    val owner: ApiUser,
    val status: TournamentStatus,
    val startDate: LocalDateTime,
    val assignedTeams: List<ApiTeam>,
    val entryRequests: List<ApiTeam>,
    val groups: List<ApiTournamentGroup>
)


enum class TournamentStatus {
    CONFIGURING, STARTED, FINISHED
}