package com.piriurna.tournamentmanager.login.domain.usecases

import com.piriurna.tournamentmanager.common.domain.AppException

sealed class AppResult<T> {

    class Loading<T>(): AppResult<T>()

    class Success<T>(val data: T): AppResult<T>()

    class Error<T>(val exception: AppException): AppResult<T>() {
        val message = exception.message
    }
}