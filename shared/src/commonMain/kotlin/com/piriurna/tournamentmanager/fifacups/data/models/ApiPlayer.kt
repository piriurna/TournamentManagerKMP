package com.piriurna.tournamentmanager.fifacups.data.models

import kotlinx.serialization.Serializable

/**
 * User but with only public fields
 */
@Serializable
data class ApiPlayer(
    val id: String,
    val nickname: String
) {
}