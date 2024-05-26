package com.piriurna.tournamentmanager.login.domain.usecases

sealed class AppResult<T> {

    class Loading<T>(): AppResult<T>()

    class Success<T>(val data: T): AppResult<T>()

    class Error<T>(val message: String?): AppResult<T>()
}