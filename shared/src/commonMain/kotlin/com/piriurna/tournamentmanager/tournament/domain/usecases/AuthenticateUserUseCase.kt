package com.piriurna.tournamentmanager.tournament.domain.usecases

import com.piriurna.tournamentmanager.tournament.data.ApiResult
import com.piriurna.tournamentmanager.tournament.domain.models.User
import com.piriurna.tournamentmanager.tournament.domain.repositories.TournamentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthenticateUserUseCase(
    private val tournamentRepository: TournamentRepository
) {


    suspend operator fun invoke(email: String, nickname: String): Flow<AppResult<User>> {
        return flow {
            emit(AppResult.Loading())

            val result = tournamentRepository.registerUser(email = email, nickname = nickname)

            when {
                result.isSuccess -> emit(AppResult.Success(result.getOrThrow()))

                result.isFailure -> emit(AppResult.Error(result.exceptionOrNull()?.message))
            }
        }
    }
}