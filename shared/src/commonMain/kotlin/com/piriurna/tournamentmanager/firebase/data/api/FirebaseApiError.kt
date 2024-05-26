package com.piriurna.tournamentmanager.firebase.data.api

import com.piriurna.tournamentmanager.common.data.ApiResult

sealed interface FirebaseApiError {
    class WrongCredentials<T>: ApiResult.Error<T>("Wrong Email/Password", 400)

    class UserAlreadyCreated<T>: ApiResult.Error<T>("Conflicting email", 400)

    class PasswordTooWeak<T>: ApiResult.Error<T>("Password too Weak", 400)

    class GenericError<T>(message: String? = null): ApiResult.Error<T>(message?:"Unknown Error", 400)
}
