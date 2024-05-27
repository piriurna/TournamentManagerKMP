package com.piriurna.tournamentmanager.common.domain

open class AppException(message: String?): Exception(message)

data object GenericException: AppException("Unknown Error")