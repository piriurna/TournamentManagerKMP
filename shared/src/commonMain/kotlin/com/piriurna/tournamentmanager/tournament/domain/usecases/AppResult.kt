package com.piriurna.tournamentmanager.tournament.domain.usecases

sealed class AppResult<T> {

    class Loading<T>(): AppResult<T>()

    class Success<T>(data: T): AppResult<T>()

    class Error<T>(val message: String?): AppResult<T>()
}