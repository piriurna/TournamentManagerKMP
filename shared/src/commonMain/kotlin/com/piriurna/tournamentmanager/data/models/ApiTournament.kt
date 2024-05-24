package com.piriurna.tournamentmanager.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiTournament(
    val id: String,
    val name: String,
    val owner: ApiOwner
)