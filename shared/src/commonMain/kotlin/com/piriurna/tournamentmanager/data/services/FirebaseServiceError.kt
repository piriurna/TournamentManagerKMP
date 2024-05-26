package com.piriurna.tournamentmanager.data.services

import com.piriurna.tournamentmanager.data.ApiResult

sealed interface FirebaseServiceError {
    class WrongCredentials<T>: ApiResult.Error<T>("Wrong Email/Password", 400)

    class UserAlreadyCreated<T>: ApiResult.Error<T>("Conflicting email", 400)

    class PasswordTooWeak<T>: ApiResult.Error<T>("Password too Weak", 400)

    class GenericError<T>: ApiResult.Error<T>("Unknown Error", 400)
}
