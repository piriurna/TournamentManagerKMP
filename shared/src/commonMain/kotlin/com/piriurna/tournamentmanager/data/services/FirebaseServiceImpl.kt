package com.piriurna.tournamentmanager.data.services

import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.domain.GlobalNavigator
import com.piriurna.tournamentmanager.domain.services.FirebaseService
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuthException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

class FirebaseServiceImpl: FirebaseService {
    override suspend fun authenticateUser(email: String, password: String): ApiResult<AuthResult> {
        return runExpectingFirebaseExceptions(email, password) {
            val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password)
            ApiResult.Success(authResult)
        }
    }

    private suspend fun runExpectingFirebaseExceptions(
        email: String,
        password: String,
        action: suspend () -> ApiResult<AuthResult>
    ): ApiResult<AuthResult> {
        return try {
            action()
        } catch (e: FirebaseAuthUserCollisionException) {
            runExpectingFirebaseExceptions(email, password) {
                val existingAuthResult = Firebase.auth.signInWithEmailAndPassword(email, password)
                ApiResult.Success(existingAuthResult)
            }
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            ApiResult.Error("Wrong Email/Password", 400)
        } catch (e: FirebaseAuthException) {
            ApiResult.Error("Error authenticating User", 400)
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