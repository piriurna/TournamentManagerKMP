package com.piriurna.tournamentmanager.login.domain.usecases

import com.piriurna.tournamentmanager.common.data.ext.appException
import com.piriurna.tournamentmanager.common.data.ext.onFailure
import com.piriurna.tournamentmanager.fifacups.domain.models.User
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.firebase.domain.repositories.FirebaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase(
    private val tournamentRepository: TournamentRepository,
    private val firebaseApi: FirebaseRepository
) {
    suspend operator fun invoke(email: String, nickname: String, password: String): Flow<AppResult<User>> {
        return flow {
            emit(AppResult.Loading())

            firebaseApi.getLoggedInUser()
                .onSuccess {
                    val userResponse = tournamentRepository.checkUser()
                    if(userResponse.isSuccess) {
                        emit(AppResult.Success(userResponse.getOrThrow()))
                        return@flow
                    }
                }

            firebaseApi.registerUser(email, password)
                .onSuccess {
                    val registerUserResult =
                        tournamentRepository.registerUser(email = email, nickname = nickname)

                    when {
                        registerUserResult.isSuccess -> emit(AppResult.Success(registerUserResult.getOrThrow()))

                        registerUserResult.isFailure -> {
                            emit(AppResult.Error(registerUserResult.appException()))
                            firebaseApi.deleteUserRegistration()
                        }
                    }
                }
                .onFailure {
                    emit(AppResult.Error(it))
                    return@flow
                }




        }
    }
}