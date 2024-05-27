package com.piriurna.tournamentmanager.login.domain.usecases

import com.piriurna.tournamentmanager.common.data.ext.appException
import com.piriurna.tournamentmanager.fifacups.domain.models.User
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.firebase.domain.repositories.FirebaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUserUseCase(
    private val tournamentRepository: TournamentRepository,
    private val firebaseApi: FirebaseRepository
) {
    suspend operator fun invoke(email: String, password: String): Flow<AppResult<User>> {
        return flow {
            emit(AppResult.Loading())

            // If user already logged in just return current user
            firebaseApi.getLoggedInUser().onSuccess {
                val userResponse = tournamentRepository.checkUser()
                if(userResponse.isSuccess) {
                    emit(AppResult.Success(userResponse.getOrThrow()))
                    return@flow
                }
            }

            // Try to authenticate user with firebase
            val firebaseRegisterResult = firebaseApi.authenticateUser(email, password)

            if(firebaseRegisterResult.isFailure) { // todo: improve this to handle each exception in a way
                emit(AppResult.Error(firebaseRegisterResult.appException()))
                return@flow
            }


            // Check if the user is already created in the backend
            val apiUserResponse = tournamentRepository.checkUser()


            if(apiUserResponse.isSuccess) {
                if(firebaseRegisterResult.isSuccess) {
                    emit(AppResult.Success(apiUserResponse.getOrThrow()))
                }
            } else {
                emit(AppResult.Error(apiUserResponse.appException()))
            }
        }
    }
}