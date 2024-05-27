package com.piriurna.tournamentmanager.firebase.domain.repositories

import com.piriurna.tournamentmanager.common.domain.AppException

sealed class FirebaseException(message: String?): AppException(message) {

    data object WrongCredentialsException: FirebaseException("Wrong Email/Password")

    data object UserAlreadyCreatedException: FirebaseException("Conflicting email")

    data object PasswordTooWeakException: FirebaseException("Password too Weak")

    data object UserNotLoggedInException: FirebaseException("User not logged in")
}