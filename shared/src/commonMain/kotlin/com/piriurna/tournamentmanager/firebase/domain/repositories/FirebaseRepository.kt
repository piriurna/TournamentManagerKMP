package com.piriurna.tournamentmanager.firebase.domain.repositories

import dev.gitlive.firebase.auth.FirebaseUser

interface FirebaseRepository {

    suspend fun registerUser(email: String, password: String): Result<FirebaseUser>

    suspend fun authenticateUser(email: String, password: String): Result<FirebaseUser>

    suspend fun deleteUserRegistration(): Result<Unit>

    suspend fun getAuthToken(): Result<String>

    suspend fun getLoggedInUser(): Result<FirebaseUser>

    suspend fun logOutUser(): Result<Unit>
}