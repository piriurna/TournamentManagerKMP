package com.piriurna.tournamentmanager.tournament.data.api

import com.piriurna.tournamentmanager.tournament.data.ApiResult
import com.piriurna.tournamentmanager.tournament.data.models.ApiTeam
import com.piriurna.tournamentmanager.tournament.data.models.ApiUser

interface FifaCupsApi {
    suspend fun registerUser(email: String, nickname: String): ApiResult<ApiUser>

    suspend fun registerTeam(name: String, imageUrl: String): ApiResult<ApiTeam>
}