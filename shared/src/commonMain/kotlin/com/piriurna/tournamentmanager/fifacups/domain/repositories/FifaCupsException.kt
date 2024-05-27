package com.piriurna.tournamentmanager.fifacups.domain.repositories

import com.piriurna.tournamentmanager.common.domain.AppException

sealed class FifaCupsException(message: String): AppException(message = message) {

    data object NoAuthTokenException: FifaCupsException("No Auth Token")


    data object ServiceUnavailableException: FifaCupsException("Service Unavailable")
}