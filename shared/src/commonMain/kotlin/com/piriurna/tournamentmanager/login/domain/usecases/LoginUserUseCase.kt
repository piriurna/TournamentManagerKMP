package com.piriurna.tournamentmanager.login.domain.usecases

import com.piriurna.tournamentmanager.common.data.ApiResult
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.firebase.domain.api.FirebaseApi
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUserUseCase(
    private val tournamentRepository: TournamentRepository,
    private val firebaseApi: FirebaseApi
) {
    suspend operator fun invoke(email: String, password: String): Flow<AppResult<FirebaseUser>> {
        return flow {
            emit(AppResult.Loading())

            // If user already logged in just return current user
            firebaseApi.getLoggedInUser()?.let {
                emit(AppResult.Success(it))
                return@flow
            }

            // Try to authenticate user with firebase
            val firebaseRegisterResult = firebaseApi.authenticateUser(email, password)

            if(firebaseRegisterResult is ApiResult.Error) {
                emit(AppResult.Error(firebaseRegisterResult.message))
                return@flow
            }


            // Check if the user is already created in the backend
            val hasUserCreatedResponse = tournamentRepository.checkUser()


            if(hasUserCreatedResponse.isSuccess) {
                if(firebaseRegisterResult is ApiResult.Success) {
                    emit(AppResult.Success(firebaseRegisterResult.result?.user!!))
                }
            } else {
                emit(AppResult.Error(hasUserCreatedResponse.exceptionOrNull()?.message ?: ""))
            }
        }
    }
}