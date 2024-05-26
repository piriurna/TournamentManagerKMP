package com.piriurna.tournamentmanager.data

import com.piriurna.tournamentmanager.data.models.ApiUser

sealed class ApiResult<T>(
    val status: Int,
    val result: T?
) {

    class Success<T>(result: T): ApiResult<T>(200, result)

    abstract class Error<T>(val message: String, status: Int): ApiResult<T>(status, null)
}