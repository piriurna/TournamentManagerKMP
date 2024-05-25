package com.piriurna.tournamentmanager.domain.usecases

import com.piriurna.tournamentmanager.domain.models.Tournament
import com.piriurna.tournamentmanager.domain.repositories.TournamentRepository
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