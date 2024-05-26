package com.piriurna.tournamentmanager.firebase.data.api

import com.piriurna.tournamentmanager.common.data.ApiResult
import com.piriurna.tournamentmanager.common.domain.GlobalNavigator
import com.piriurna.tournamentmanager.firebase.domain.api.FirebaseApi
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuthException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.FirebaseAuthWeakPasswordException
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

class FirebaseApiImpl: FirebaseApi {

    override suspend fun registerUser(email: String, password: String): ApiResult<AuthResult> {
        return try {
            val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password)
            ApiResult.Success(authResult)
        } catch (e: FirebaseAuthUserCollisionException) {
            FirebaseApiError.UserAlreadyCreated()
        } catch (e: FirebaseAuthWeakPasswordException) {
            FirebaseApiError.PasswordTooWeak()
        }
    }

    override suspend fun authenticateUser(email: String, password: String): ApiResult<AuthResult> {
        return try {
            val existingAuthResult = Firebase.auth.signInWithEmailAndPassword(email, password)
            ApiResult.Success(existingAuthResult)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            FirebaseApiError.WrongCredentials()
        } catch (e: FirebaseAuthException) {
            FirebaseApiError.GenericError()
        }
    }

    override suspend fun deleteUserRegistration(): ApiResult<Unit> {
        return if(Firebase.auth.currentUser?.delete() != null) {
            ApiResult.Success(Unit)
        } else {
            FirebaseApiError.GenericError()
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