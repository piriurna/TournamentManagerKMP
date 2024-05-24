package com.piriurna.tournamentmanager.data.repositories

import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.data.api.FifaCupsApi
import com.piriurna.tournamentmanager.data.api.models.CreateTeamRequestBody
import com.piriurna.tournamentmanager.data.api.models.CreateUserRequestBody
import com.piriurna.tournamentmanager.data.mappers.toUser
import com.piriurna.tournamentmanager.domain.models.Owner
import com.piriurna.tournamentmanager.domain.models.Team
import com.piriurna.tournamentmanager.domain.models.User
import com.piriurna.tournamentmanager.domain.repositories.TournamentRepository

class TournamentRepositoryImpl(
    private val fifaCupsApi: FifaCupsApi
): TournamentRepository {
    override suspend fun registerUser(email: String, nickname: String): Result<User> {
        return when(val user = fifaCupsApi.registerUser(CreateUserRequestBody(nickname, email))) {
            is ApiResult.Success -> Result.success(User(id = "", nickname, email))

            is ApiResult.Error -> Result.failure(Throwable(user.message))
        }
    }

    override suspend fun createTeam(name: String, imageUrl: String): Result<Team> {
        return when(val team = fifaCupsApi.registerTeam(CreateTeamRequestBody(name, imageUrl))) {
            is ApiResult.Success -> Result.success(Team(name = team.result!!, id = "", owner = Owner(id = "", name = "")))

            is ApiResult.Error -> Result.failure(Throwable(team.message))
        }
    }
}