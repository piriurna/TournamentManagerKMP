package com.piriurna.tournamentmanager.fifacups.domain.usecases

import com.piriurna.tournamentmanager.common.data.ext.appException
import com.piriurna.tournamentmanager.fifacups.domain.models.Tournament
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.login.domain.usecases.AppResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTournamentsUseCase(
    private val tournamentRepository: TournamentRepository
) {


    suspend operator fun invoke(): Flow<AppResult<List<Tournament>>> {
        return flow {
            emit(AppResult.Loading())

            val result = tournamentRepository.getTournamentsForUser()

            when {
                result.isSuccess -> {
                    val tournamentsByDate = result
                        .getOrThrow()
                    emit(AppResult.Success(tournamentsByDate))
                }

                result.isFailure -> emit(AppResult.Error(result.appException()))
            }
        }
    }
}