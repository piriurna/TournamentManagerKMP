package com.piriurna.tournamentmanager.firebase.data.repositories

import com.piriurna.tournamentmanager.common.data.ApiResult
import com.piriurna.tournamentmanager.firebase.domain.api.FirebaseApi
import com.piriurna.tournamentmanager.firebase.domain.repositories.FirebaseRepository
import dev.gitlive.firebase.auth.FirebaseUser

class FirebaseRepositoryImpl(
    private val firebaseApi: FirebaseApi
): FirebaseRepository {
    override suspend fun registerUser(email: String, password: String): Result<FirebaseUser> {
        val response = firebaseApi.registerUser(email, password)
        return when {
            response is ApiResult.Success && response.result?.user != null -> {
                Result.success(response.result.user!!)
            }

            response is ApiResult.Error -> {
                Result.failure(response.exception)
            }

            else -> {
                Result.failure(Exception())
            }
        }
    }

    override suspend fun authenticateUser(email: String, password: String): Result<FirebaseUser> {
        val response = firebaseApi.authenticateUser(email, password)
        return when {
            response is ApiResult.Success && response.result?.user != null-> {
                Result.success(response.result.user!!)
            }

            response is ApiResult.Error -> {
                Result.failure(response.exception)
            }

            else -> {
                Result.failure(Exception())
            }
        }
    }

    override suspend fun deleteUserRegistration(): Result<Unit> {
        return when(val response = firebaseApi.deleteUserRegistration()) {
            is ApiResult.Success -> {
                Result.success(Unit)
            }

            is ApiResult.Error -> {
                Result.failure(response.exception)
            }
        }
    }

    override suspend fun getAuthToken(): Result<String> {
        return when(val response = firebaseApi.getAuthToken()) {
            is ApiResult.Success -> {
                Result.success(response.result!!)
            }

            is ApiResult.Error -> {
                Result.failure(response.exception)
            }
        }
    }

    override suspend fun getLoggedInUser(): Result<FirebaseUser> {
        return when(val response = firebaseApi.getLoggedInUser()) {
            is ApiResult.Success -> {
                Result.success(response.result!!)
            }

            is ApiResult.Error -> {
                Result.failure(response.exception)
            }
        }
    }

    override suspend fun logOutUser(): Result<Unit> {
        return when(val response = firebaseApi.logOutUser()) {
            is ApiResult.Success -> {
                Result.success(response.result!!)
            }

            is ApiResult.Error -> {
                Result.failure(response.exception)
            }
        }
    }
}