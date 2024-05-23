package com.piriurna.tournamentmanager.tournament.domain.services

import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseUser

interface FirebaseService {
    suspend fun authenticateUser(email: String, password: String): AuthResult?

    suspend fun getAuthToken(): String?

    suspend fun getLoggedInUser(): FirebaseUser?

    suspend fun logOutUser()
}