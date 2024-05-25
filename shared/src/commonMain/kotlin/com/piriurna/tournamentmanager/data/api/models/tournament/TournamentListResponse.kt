package com.piriurna.tournamentmanager.data.api.models.tournament

import com.piriurna.tournamentmanager.data.models.ApiTournament
import kotlinx.serialization.Serializable

@Serializable
data class TournamentListResponse(
    val tournaments: List<ApiTournament>
)