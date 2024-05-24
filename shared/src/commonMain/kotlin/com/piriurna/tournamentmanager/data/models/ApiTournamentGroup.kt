package com.piriurna.tournamentmanager.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiTournamentGroup(
    val id: String,
    val apiTournament: ApiTournament
)