package com.piriurna.tournamentmanager.common.data

import com.piriurna.tournamentmanager.fifacups.data.models.ApiUser

sealed class ApiResult<T>(
    val status: Int,
    val result: T?
) {

    class Success<T>(result: T): ApiResult<T>(200, result)

    open class Error<T>(val message: String, status: Int): ApiResult<T>(status, null)
}