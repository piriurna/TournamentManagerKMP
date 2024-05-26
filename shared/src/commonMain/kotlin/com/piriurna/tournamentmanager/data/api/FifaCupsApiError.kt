package com.piriurna.tournamentmanager.data.api

import com.piriurna.tournamentmanager.data.ApiResult


sealed class FifaCupsApiError<T>(
    message: String,
    status: Int
): ApiResult.Error<T>(message, status) {
    class NoAuthToken <T>(message: String? = null): FifaCupsApiError<T>(message?:"No Auth Token", 500)


    class ServiceUnavailable <T>(message: String? = null): FifaCupsApiError<T>(message?:"Service Unavailable", 503)


    class GenericError <T>(message: String? = null): FifaCupsApiError<T>(message?:"Unknown Error", 500)

}
