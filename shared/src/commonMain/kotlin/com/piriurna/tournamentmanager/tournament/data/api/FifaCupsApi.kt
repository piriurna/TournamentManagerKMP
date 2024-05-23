package com.piriurna.tournamentmanager.tournament.data.api

import com.piriurna.tournamentmanager.tournament.data.ApiResult
import com.piriurna.tournamentmanager.tournament.data.api.models.CreateTeamRequestBody
import com.piriurna.tournamentmanager.tournament.data.api.models.CreateUserRequestBody
import com.piriurna.tournamentmanager.tournament.data.models.ApiTeam
import com.piriurna.tournamentmanager.tournament.data.models.ApiUser

interface FifaCupsApi {
    suspend fun registerUser(createUserRequestBody: CreateUserRequestBody): ApiResult<ApiUser>

    suspend fun registerTeam(createTeamRequestBody: CreateTeamRequestBody): ApiResult<ApiTeam>
}