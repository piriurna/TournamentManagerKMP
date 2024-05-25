package com.piriurna.tournamentmanager.data.services

import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.domain.GlobalNavigator
import com.piriurna.tournamentmanager.domain.services.FirebaseService
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuthException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.FirebaseAuthWeakPasswordException
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

class FirebaseServiceImpl: FirebaseService {

    override suspend fun registerUser(email: String, password: String): ApiResult<AuthResult> {
        return try {
            val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password)
            ApiResult.Success(authResult)
        } catch (e: FirebaseAuthUserCollisionException) {
            ApiResult.Error(e.message?:"Email already taken", 400)
        } catch (e: FirebaseAuthWeakPasswordException) {
            ApiResult.Error(e.message?:"Password is too weak", 400)
        }
    }

    override suspend fun authenticateUser(email: String, password: String): ApiResult<AuthResult> {
        return try {
            val existingAuthResult = Firebase.auth.signInWithEmailAndPassword(email, password)
            ApiResult.Success(existingAuthResult)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            ApiResult.Error(e.message?:"Wrong Email/Password", 400)
        } catch (e: FirebaseAuthException) {
            ApiResult.Error(e.message?:"Error authenticating User", 400)
        }
    }

    override suspend fun deleteUserRegistration(): ApiResult<Unit> {
        return if(Firebase.auth.currentUser?.delete() != null) {
            ApiResult.Success(Unit)
        } else {
            ApiResult.Error("Error deleting user", status = 500)
        }
    }
    override suspend fun getAuthToken(): String? {
        if(Firebase.auth.currentUser == null) return null

        return Firebase.auth.currentUser?.getIdToken(true)
    }

    override suspend fun getLoggedInUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }

    override suspend fun logOutUser() {
        Firebase.auth.signOut()
        GlobalNavigator.logout()
    }
}