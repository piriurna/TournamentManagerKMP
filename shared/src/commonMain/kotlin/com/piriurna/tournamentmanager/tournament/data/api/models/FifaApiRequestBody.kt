package com.piriurna.tournamentmanager.tournament.data.api.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequestBody(
    val nickname: String,
    val email: String
)

@Serializable
data class CreateTeamRequestBody(
    val name: String,
    val imageUrl: String
)