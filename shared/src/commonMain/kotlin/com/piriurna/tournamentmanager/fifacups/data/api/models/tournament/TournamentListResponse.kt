package com.piriurna.tournamentmanager.fifacups.data.api.models.tournament

import com.piriurna.tournamentmanager.fifacups.data.models.ApiTournament
import kotlinx.serialization.Serializable

@Serializable
data class TournamentListResponse(
    val tournaments: List<ApiTournament>
)