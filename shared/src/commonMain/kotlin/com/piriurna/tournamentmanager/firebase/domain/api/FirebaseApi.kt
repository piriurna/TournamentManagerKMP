package com.piriurna.tournamentmanager.firebase.domain.api

import com.piriurna.tournamentmanager.common.data.ApiResult
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseUser

interface FirebaseApi {
    suspend fun registerUser(email: String, password: String): ApiResult<AuthResult>

    suspend fun authenticateUser(email: String, password: String): ApiResult<AuthResult>

    suspend fun deleteUserRegistration(): ApiResult<Unit>

    suspend fun getAuthToken(): String?

    suspend fun getLoggedInUser(): FirebaseUser?

    suspend fun logOutUser()
}