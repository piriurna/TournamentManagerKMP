package com.piriurna.tournamentmanager.fifacups.domain.repositories

import com.piriurna.tournamentmanager.common.data.ApiResult


sealed interface FifaCupsApiError {
    class NoAuthToken <T>: ApiResult.Error<T>("No Auth Token", 500)


    class ServiceUnavailable <T>: ApiResult.Error<T>("Service Unavailable", 503)


    class GenericError <T>(message: String? = null): ApiResult.Error<T>(message?:"Unknown Error", 500)

}
