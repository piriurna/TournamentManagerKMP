package com.piriurna.tournamentmanager.common.data.ext

import com.piriurna.tournamentmanager.common.domain.AppException
import com.piriurna.tournamentmanager.common.domain.GenericException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

fun <T> Result<T>.appException(): AppException {
    return (this.exceptionOrNull() as? AppException)?: GenericException
}


@OptIn(ExperimentalContracts::class)
@SinceKotlin("1.3")
inline fun <T> Result<T>.onFailure(action: (exception: AppException) -> Unit): Result<T> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    exceptionOrNull()?.let { action(it as AppException) }
    return this
}