package com.piriurna.tournamentmanager.login.domain.usecases

import com.piriurna.tournamentmanager.common.data.ApiResult
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.firebase.domain.api.FirebaseApi
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase(
    private val tournamentRepository: TournamentRepository,
    private val firebaseApi: FirebaseApi
) {
    suspend operator fun invoke(email: String, nickname: String, password: String): Flow<AppResult<FirebaseUser>> {
        return flow {
            emit(AppResult.Loading())

            firebaseApi.getLoggedInUser()?.let {
                emit(AppResult.Success(it))
                return@flow
            }

            val firebaseRegisterResult = firebaseApi.registerUser(email, password)

            if(firebaseRegisterResult is ApiResult.Success) {
                val registerUserResult =
                    tournamentRepository.registerUser(email = email, nickname = nickname)

                when {
                    registerUserResult.isSuccess -> emit(AppResult.Success(firebaseRegisterResult.result!!.user!!))

                    registerUserResult.isFailure -> {
                        emit(AppResult.Error(registerUserResult.exceptionOrNull()?.message))
                        firebaseApi.deleteUserRegistration()
                    }
                }
            } else if(firebaseRegisterResult is ApiResult.Error){
                emit(AppResult.Error(firebaseRegisterResult.message))
                return@flow
            }




        }
    }
}