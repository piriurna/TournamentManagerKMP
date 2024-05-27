package com.piriurna.tournamentmanager.fifacups.domain.usecases

import com.piriurna.tournamentmanager.common.data.ext.appException
import com.piriurna.tournamentmanager.fifacups.domain.models.Team
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.login.domain.usecases.AppResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CreateTeamUseCase(
    private val tournamentRepository: TournamentRepository
) {
    suspend operator fun invoke(name: String, imageUrl: String): Flow<AppResult<Team>> {
        return flow {
            emit(AppResult.Loading())

            val result = tournamentRepository.createTeam(name, imageUrl)

            when {
                result.isSuccess -> emit(AppResult.Success(result.getOrThrow()))

                result.isFailure -> emit(AppResult.Error(result.appException()))
            }
        }
    }
}