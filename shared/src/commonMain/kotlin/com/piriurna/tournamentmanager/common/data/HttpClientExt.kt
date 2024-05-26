package com.piriurna.tournamentmanager.common.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

expect val client: HttpClient


suspend inline fun <reified T> HttpResponse.bodyOrError(): ApiResult<T> {
    return if(status.isSuccess()) {
        ApiResult.Success(this.body() as T)
    } else {
        ApiResult.Error(status.description, status.value)
    }
}