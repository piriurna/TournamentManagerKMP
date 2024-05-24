package com.piriurna.tournamentmanager.data.api

import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.data.api.models.CreateTeamRequestBody
import com.piriurna.tournamentmanager.data.api.models.CreateUserRequestBody

interface FifaCupsApi {
    suspend fun registerUser(createUserRequestBody: CreateUserRequestBody): ApiResult<String> //TODO: TEMPORARY STRING RETURNING FROM API

    suspend fun registerTeam(createTeamRequestBody: CreateTeamRequestBody): ApiResult<String> //TODO: TEMPORARY STRING RETURNING FROM API
}