package com.piriurna.tournamentmanager.domain.usecases

import com.piriurna.tournamentmanager.domain.models.Tournament
import com.piriurna.tournamentmanager.domain.repositories.TournamentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDate

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

                result.isFailure -> emit(AppResult.Error(result.exceptionOrNull()?.message))
            }
        }
    }
}