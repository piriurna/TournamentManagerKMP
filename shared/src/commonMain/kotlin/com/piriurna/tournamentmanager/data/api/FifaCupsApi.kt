package com.piriurna.tournamentmanager.data.api

import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.data.api.models.CreateTeamRequestBody
import com.piriurna.tournamentmanager.data.api.models.CreateUserRequestBody
import com.piriurna.tournamentmanager.data.api.models.tournament.TournamentListResponse
import com.piriurna.tournamentmanager.data.models.ApiTeam
import com.piriurna.tournamentmanager.data.models.ApiUser

interface FifaCupsApi {
    suspend fun registerUser(createUserRequestBody: CreateUserRequestBody): ApiResult<String> //TODO: TEMPORARY STRING RETURNING FROM API

    suspend fun checkUser(): ApiResult<ApiUser>

    suspend fun registerTeam(createTeamRequestBody: CreateTeamRequestBody): ApiResult<String> //TODO: TEMPORARY STRING RETURNING FROM API

    suspend fun getUserTeams(): ApiResult<List<ApiTeam>>

    suspend fun getTournamentsForUser(): ApiResult<TournamentListResponse>
}