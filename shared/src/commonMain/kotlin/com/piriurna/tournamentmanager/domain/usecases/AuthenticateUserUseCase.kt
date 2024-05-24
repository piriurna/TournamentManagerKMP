package com.piriurna.tournamentmanager.domain.usecases

import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.domain.models.User
import com.piriurna.tournamentmanager.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.domain.services.FirebaseService
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthenticateUserUseCase(
    private val tournamentRepository: TournamentRepository,
    private val firebaseService: FirebaseService
) {
    suspend operator fun invoke(email: String, nickname: String): Flow<AppResult<FirebaseUser>> {
        return flow {
            emit(AppResult.Loading())

            val firebaseRegisterResult = firebaseService.authenticateUser(email, nickname)

            if(firebaseRegisterResult?.user == null){
                emit(AppResult.Error("Failed to create user in firebase"))
                return@flow
            }

            val result = tournamentRepository.registerUser(email = email, nickname = nickname)

            when {
                result.isSuccess -> emit(AppResult.Success(firebaseRegisterResult.user!!))

                result.isFailure -> emit(AppResult.Error(result.exceptionOrNull()?.message))
            }
        }
    }
}