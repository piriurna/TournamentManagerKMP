package com.piriurna.tournamentmanager.tournament.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiTeam(
    val id: String,
    val name: String,
    val owner: ApiOwner
)