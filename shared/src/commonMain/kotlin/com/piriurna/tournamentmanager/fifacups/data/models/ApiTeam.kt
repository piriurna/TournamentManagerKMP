package com.piriurna.tournamentmanager.fifacups.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiTeam(
    val id: String,
    val name: String,
    val owner: ApiUser,
    val players: List<ApiUser>,
    val imageUrl: String?
)