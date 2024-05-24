package com.piriurna.tournamentmanager.data.api

import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.data.api.models.CreateTeamRequestBody
import com.piriurna.tournamentmanager.data.api.models.CreateUserRequestBody
import com.piriurna.tournamentmanager.data.models.ApiTeam
import com.piriurna.tournamentmanager.data.models.ApiUser

interface FifaCupsApi {
    suspend fun registerUser(createUserRequestBody: CreateUserRequestBody): ApiResult<ApiUser>

    suspend fun registerTeam(createTeamRequestBody: CreateTeamRequestBody): ApiResult<ApiTeam>
}