package com.piriurna.tournamentmanager.tournament.data.services

import com.piriurna.tournamentmanager.tournament.domain.services.FirebaseService
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException

class FirebaseServiceImpl(
    private val firebaseAuth: FirebaseAuth
): FirebaseService {

    override suspend fun authenticateUser(email: String, password: String): AuthResult {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
        } catch (e: FirebaseAuthUserCollisionException) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
        }
    }


    override suspend fun getAuthToken(): String? {
        if(firebaseAuth.currentUser == null) return null

        return firebaseAuth.currentUser?.getIdToken(true)
    }

    override suspend fun logOutUser() {
        firebaseAuth.signOut()
    }
}