package com.piriurna.tournamentmanager.fifacups.data.repositories

import com.piriurna.tournamentmanager.common.data.ApiResult
import com.piriurna.tournamentmanager.fifacups.data.api.FifaCupsApi
import com.piriurna.tournamentmanager.fifacups.data.api.models.CreateTeamRequestBody
import com.piriurna.tournamentmanager.fifacups.data.api.models.CreateUserRequestBody
import com.piriurna.tournamentmanager.fifacups.data.mappers.toTeam
import com.piriurna.tournamentmanager.fifacups.data.mappers.toTournament
import com.piriurna.tournamentmanager.fifacups.data.mappers.toUser
import com.piriurna.tournamentmanager.fifacups.domain.models.Team
import com.piriurna.tournamentmanager.fifacups.domain.models.Tournament
import com.piriurna.tournamentmanager.fifacups.domain.models.User
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository

class TournamentRepositoryImpl(
    private val fifaCupsApi: FifaCupsApi
): TournamentRepository {
    override suspend fun registerUser(email: String, nickname: String): Result<User> {
        return when(val user = fifaCupsApi.registerUser(CreateUserRequestBody(nickname, email))) {
            is ApiResult.Success -> Result.success(user.result!!.toUser())

            is ApiResult.Error -> Result.failure(user.exception)
        }
    }

    override suspend fun checkUser(): Result<User> {
        return when(val user = fifaCupsApi.checkUser()) {
            is ApiResult.Success -> Result.success(user.result!!.toUser())

            is ApiResult.Error -> Result.failure(user.exception)
        }
    }

    override suspend fun createTeam(name: String, imageUrl: String): Result<Team> {
        return when(val team = fifaCupsApi.registerTeam(CreateTeamRequestBody(name, imageUrl))) {
            is ApiResult.Success -> Result.success(team.result!!.toTeam())

            is ApiResult.Error -> Result.failure(team.exception)
        }
    }

    override suspend fun getUserTeam(): Result<Team?> {
        return when(val response = fifaCupsApi.getUserTeams()) {
            is ApiResult.Success -> Result.success(response.result?.firstOrNull()?.toTeam())

            is ApiResult.Error -> Result.failure(response.exception)
        }
    }

    override suspend fun getUserTeamList(): Result<List<Team>> {
        return when(val response = fifaCupsApi.getUserTeams()) {
            is ApiResult.Success -> Result.success(response.result!!.map { it.toTeam() })

            is ApiResult.Error -> Result.failure(response.exception)
        }
    }

    override suspend fun getTournamentsForUser(): Result<List<Tournament>> {
        return when(val response = fifaCupsApi.getTournamentsForUser()) {
            is ApiResult.Success -> Result.success(response.result?.tournaments?.map { it.toTournament() }?: emptyList())

            is ApiResult.Error -> Result.failure(response.exception)
        }
    }
}