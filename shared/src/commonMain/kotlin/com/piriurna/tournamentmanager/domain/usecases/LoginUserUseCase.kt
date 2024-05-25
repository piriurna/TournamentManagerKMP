package com.piriurna.tournamentmanager.domain.usecases

import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.domain.services.FirebaseService
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUserUseCase(
    private val firebaseService: FirebaseService
) {
    suspend operator fun invoke(email: String, password: String): Flow<AppResult<FirebaseUser>> {
        return flow {
            emit(AppResult.Loading())

            firebaseService.getLoggedInUser()?.let {
                emit(AppResult.Success(it))
                return@flow
            }

            val firebaseRegisterResult = firebaseService.authenticateUser(email, password)

            if(firebaseRegisterResult is ApiResult.Success) {
                emit(AppResult.Success(firebaseRegisterResult.result?.user!!))
            } else if(firebaseRegisterResult is ApiResult.Error){
                emit(AppResult.Error(firebaseRegisterResult.message))
            }
        }
    }
}