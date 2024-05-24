package com.piriurna.tournamentmanager.data.models

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