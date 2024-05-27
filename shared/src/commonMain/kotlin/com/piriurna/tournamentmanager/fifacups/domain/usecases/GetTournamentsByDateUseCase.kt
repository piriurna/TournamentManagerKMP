package com.piriurna.tournamentmanager.fifacups.domain.usecases

import com.piriurna.tournamentmanager.common.data.ext.appException
import com.piriurna.tournamentmanager.login.domain.usecases.AppResult
import com.piriurna.tournamentmanager.fifacups.domain.models.Tournament
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias TournamentsByDate = Map<String, List<Tournament>>

class GetTournamentsByDateUseCase(
    private val tournamentRepository: TournamentRepository
) {


    suspend operator fun invoke(): Flow<AppResult<TournamentsByDate>> {
        return flow {
            emit(AppResult.Loading())

            val result = tournamentRepository.getTournamentsForUser()

            when {
                result.isSuccess -> {
                    val tournamentsByDate = result
                        .getOrThrow()
                        .groupBy { it.date.date }
                        .map { (localDate, tournaments) ->
                            "${localDate.dayOfMonth}/${localDate.month.name}/${localDate.year}" to tournaments
                        }
                        .toMap()
                    emit(AppResult.Success(tournamentsByDate))
                }

                result.isFailure -> emit(AppResult.Error(result.appException()))
            }
        }
    }
}