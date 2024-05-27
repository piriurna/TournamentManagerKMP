package com.piriurna.tournamentmanager.fifacups.domain.usecases

import com.piriurna.tournamentmanager.common.data.ext.onFailure
import com.piriurna.tournamentmanager.fifacups.domain.models.User
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.login.domain.usecases.AppResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLoggedInUserUseCase(
    private val fifaCupsRepository: TournamentRepository
) {

    suspend operator fun invoke(): Flow<AppResult<User>> {
        return flow {
            emit(AppResult.Loading())

            fifaCupsRepository.checkUser()
                .onSuccess {
                    emit(AppResult.Success(it))
                }
                .onFailure {
                    emit(AppResult.Error(it))
                }
        }
    }
}