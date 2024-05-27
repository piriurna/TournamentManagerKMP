package com.piriurna.tournamentmanager.fifacups.domain.repositories

import com.piriurna.tournamentmanager.common.data.ApiResult
import com.piriurna.tournamentmanager.common.domain.AppException


sealed class FifaCupsApiError<T>(
    exception: AppException,
    status: Int
): ApiResult.Error<T>(exception, status) {
    class NoAuthToken <T>: FifaCupsApiError<T>(FifaCupsException.NoAuthTokenException, 500)


    class ServiceUnavailable <T>: FifaCupsApiError<T>(FifaCupsException.ServiceUnavailableException, 503)
}
