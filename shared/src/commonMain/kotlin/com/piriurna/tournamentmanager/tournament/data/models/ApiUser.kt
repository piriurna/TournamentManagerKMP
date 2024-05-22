package com.piriurna.tournamentmanager.tournament.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    val id: String,
    val nickname: String,
    val email: String
)