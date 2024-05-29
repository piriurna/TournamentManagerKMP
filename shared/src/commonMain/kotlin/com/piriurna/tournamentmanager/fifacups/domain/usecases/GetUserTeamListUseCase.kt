package com.piriurna.tournamentmanager.fifacups.domain.usecases

import com.piriurna.tournamentmanager.common.data.ext.appException
import com.piriurna.tournamentmanager.login.domain.usecases.AppResult
import com.piriurna.tournamentmanager.fifacups.domain.models.Team
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserTeamListUseCase(
    private val tournamentRepository: TournamentRepository
) {

    suspend operator fun invoke(): Flow<AppResult<List<Team>>> {
        return flow {
            emit(AppResult.Loading())


            val result = tournamentRepository.getUserTeamList()

            if(result.isSuccess) {
                emit(AppResult.Success(result.getOrThrow()))
            } else {
                emit(AppResult.Error(result.appException()))
            }
        }
    }
}