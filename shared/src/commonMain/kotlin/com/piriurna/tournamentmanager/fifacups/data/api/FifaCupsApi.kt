package com.piriurna.tournamentmanager.fifacups.data.api

import com.piriurna.tournamentmanager.common.data.ApiResult
import com.piriurna.tournamentmanager.fifacups.data.api.models.CreateTeamRequestBody
import com.piriurna.tournamentmanager.fifacups.data.api.models.CreateUserRequestBody
import com.piriurna.tournamentmanager.fifacups.data.api.models.tournament.TournamentListResponse
import com.piriurna.tournamentmanager.fifacups.data.models.ApiTeam
import com.piriurna.tournamentmanager.fifacups.data.models.ApiUser

interface FifaCupsApi {

    suspend fun registerUser(createUserRequestBody: CreateUserRequestBody): ApiResult<ApiUser>

    suspend fun checkUser(): ApiResult<ApiUser>

    suspend fun registerTeam(createTeamRequestBody: CreateTeamRequestBody): ApiResult<ApiTeam>

    suspend fun getUserTeams(): ApiResult<List<ApiTeam>>

    suspend fun getTournamentsForUser(): ApiResult<TournamentListResponse>
}