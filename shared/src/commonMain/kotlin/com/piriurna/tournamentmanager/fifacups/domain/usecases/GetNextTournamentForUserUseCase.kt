package com.piriurna.tournamentmanager.fifacups.domain.usecases

import com.piriurna.tournamentmanager.login.domain.usecases.AppResult
import com.piriurna.tournamentmanager.fifacups.domain.models.Tournament
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNextTournamentForUserUseCase(
    private val tournamentRepository: TournamentRepository
) {

    suspend operator fun invoke(): Flow<AppResult<Tournament?>> {
        return flow {
            emit(AppResult.Loading())

            val result = tournamentRepository.getTournamentsForUser()

            if(result.isSuccess) {
                emit(AppResult.Success(result.getOrThrow().firstOrNull()))
            } else {
                emit(AppResult.Error(result.exceptionOrNull()?.message))
            }
        }
    }
}