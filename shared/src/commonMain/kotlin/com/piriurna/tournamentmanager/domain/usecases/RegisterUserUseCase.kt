package com.piriurna.tournamentmanager.domain.usecases

import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.domain.services.FirebaseService
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase(
    private val tournamentRepository: TournamentRepository,
    private val firebaseService: FirebaseService
) {
    suspend operator fun invoke(email: String, nickname: String, password: String): Flow<AppResult<FirebaseUser>> {
        return flow {
            emit(AppResult.Loading())

            firebaseService.getLoggedInUser()?.let {
                emit(AppResult.Success(it))
                return@flow
            }

            val firebaseRegisterResult = firebaseService.registerUser(email, password)

            if(firebaseRegisterResult is ApiResult.Success) {
                val registerUserResult =
                    tournamentRepository.registerUser(email = email, nickname = nickname)

                when {
                    registerUserResult.isSuccess -> emit(AppResult.Success(firebaseRegisterResult.result!!.user!!))

                    registerUserResult.isFailure -> {
                        emit(AppResult.Error(registerUserResult.exceptionOrNull()?.message))
                        firebaseService.deleteUserRegistration()
                    }
                }
            } else if(firebaseRegisterResult is ApiResult.Error){
                emit(AppResult.Error(firebaseRegisterResult.message))
                return@flow
            }




        }
    }
}