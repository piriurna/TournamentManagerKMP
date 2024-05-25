package com.piriurna.tournamentmanager.domain.usecases

import com.piriurna.tournamentmanager.domain.models.Team
import com.piriurna.tournamentmanager.domain.repositories.TournamentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserTeamUseCase(
    private val tournamentRepository: TournamentRepository
) {

    suspend operator fun invoke(): Flow<AppResult<Team?>> {
        return flow {
            emit(AppResult.Loading())


            val result = tournamentRepository.getUserTeam()

            if(result.isSuccess) {
                emit(AppResult.Success(result.getOrThrow()))
            } else {
                emit(AppResult.Error(result.exceptionOrNull()?.message))
            }
        }
    }
}