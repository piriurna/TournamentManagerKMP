package com.piriurna.tournamentmanager.firebase.data.api

import com.piriurna.tournamentmanager.common.data.ApiResult
import com.piriurna.tournamentmanager.common.domain.AppException
import com.piriurna.tournamentmanager.common.domain.GenericException
import com.piriurna.tournamentmanager.firebase.domain.repositories.FirebaseException

sealed class FirebaseApiError<T>(
    exception: AppException,
    status: Int
): ApiResult.Error<T>(exception, status) {
    class WrongCredentials<T>: FirebaseApiError<T>(FirebaseException.WrongCredentialsException, 400)

    class UserAlreadyCreated<T>: FirebaseApiError<T>(FirebaseException.UserAlreadyCreatedException, 400)

    class PasswordTooWeak<T>: FirebaseApiError<T>(FirebaseException.PasswordTooWeakException, 400)

    class UserNotLoggedIn<T>: FirebaseApiError<T>(FirebaseException.UserNotLoggedInException, 401)

    class GenericError<T>: FirebaseApiError<T>(GenericException, 500)
}
