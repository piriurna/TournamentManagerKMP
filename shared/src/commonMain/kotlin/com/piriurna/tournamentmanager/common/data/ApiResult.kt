package com.piriurna.tournamentmanager.common.data

import com.piriurna.tournamentmanager.common.domain.AppException
import com.piriurna.tournamentmanager.common.domain.GenericException

sealed class ApiResult<T>(
    val status: Int,
    val result: T?
) {

    class Success<T>(result: T): ApiResult<T>(200, result)

    open class Error<T>(val exception: AppException, status: Int): ApiResult<T>(status, null) {
        val message = exception.message
    }

    class GenericError <T>: Error<T>(GenericException, 500)
}