package com.piriurna.tournamentmanager.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiTeam(
    val id: String,
    val name: String,
    val owner: ApiPlayer,
    val players: List<ApiPlayer>
)