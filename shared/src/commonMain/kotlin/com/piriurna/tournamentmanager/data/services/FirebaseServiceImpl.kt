package com.piriurna.tournamentmanager.data.services

import com.piriurna.tournamentmanager.domain.services.FirebaseService
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

class FirebaseServiceImpl: FirebaseService {
    override suspend fun authenticateUser(email: String, password: String): AuthResult? {
        return try {
            Firebase.auth.createUserWithEmailAndPassword(email, password)
        } catch (e: FirebaseAuthUserCollisionException) {
            Firebase.auth.signInWithEmailAndPassword(email, password)
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
    }
}