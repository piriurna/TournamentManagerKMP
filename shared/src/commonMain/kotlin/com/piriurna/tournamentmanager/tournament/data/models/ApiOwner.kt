package com.piriurna.tournamentmanager.tournament.data.models

import kotlinx.serialization.Serializable

/**
 * User but with only public fields
 */
@Serializable
data class ApiOwner(
    val id: String,
    val name: String
) {
}