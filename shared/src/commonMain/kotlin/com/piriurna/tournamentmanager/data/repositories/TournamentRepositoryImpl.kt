package com.piriurna.tournamentmanager.data.repositories

import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.data.api.FifaCupsApi
import com.piriurna.tournamentmanager.data.api.models.CreateTeamRequestBody
import com.piriurna.tournamentmanager.data.api.models.CreateUserRequestBody
import com.piriurna.tournamentmanager.data.mappers.toTeam
import com.piriurna.tournamentmanager.data.mappers.toUser
import com.piriurna.tournamentmanager.domain.models.Match
import com.piriurna.tournamentmanager.domain.models.Team
import com.piriurna.tournamentmanager.domain.models.Tournament
import com.piriurna.tournamentmanager.domain.models.User
import com.piriurna.tournamentmanager.domain.repositories.TournamentRepository

class TournamentRepositoryImpl(
    private val fifaCupsApi: FifaCupsApi
): TournamentRepository {
    override suspend fun registerUser(email: String, nickname: String): Result<User> {
        return when(val user = fifaCupsApi.registerUser(CreateUserRequestBody(nickname, email))) {
            is ApiResult.Success -> Result.success(user.result!!.toUser())

            is ApiResult.Error -> Result.failure(Throwable(user.message))
        }
    }

    override suspend fun createTeam(name: String, imageUrl: String): Result<Team> {
        return when(val user = fifaCupsApi.registerTeam(CreateTeamRequestBody(name, imageUrl))) {
            is ApiResult.Success -> Result.success(user.result!!.toTeam())

            is ApiResult.Error -> Result.failure(Throwable(user.message))
        }
    }
}