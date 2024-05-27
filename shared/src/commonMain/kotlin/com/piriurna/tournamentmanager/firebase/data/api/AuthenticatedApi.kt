package com.piriurna.tournamentmanager.firebase.data.api

import com.piriurna.tournamentmanager.common.data.ApiResult
import com.piriurna.tournamentmanager.fifacups.domain.repositories.FifaCupsApiError
import com.piriurna.tournamentmanager.firebase.domain.api.FirebaseApi
import io.ktor.client.plugins.ResponseException
import io.ktor.http.HttpStatusCode.Companion.ServiceUnavailable

open class AuthenticatedApi(private val firebaseApi: FirebaseApi) {

    suspend fun <T> withAuthToken(authenticatedAction: suspend (authToken: String) -> ApiResult<T>): ApiResult<T> {
        val authTokenResponse = firebaseApi.getAuthToken()

        return if(authTokenResponse is ApiResult.Error) {
            FifaCupsApiError.NoAuthToken()
        } else {
            try {
                authenticatedAction(authTokenResponse.result!!)
            } catch (e: ResponseException) {
                if(e.response.status == ServiceUnavailable) {
                    FifaCupsApiError.ServiceUnavailable()
                } else {
                    ApiResult.GenericError()
                }
            }
        }
    }
}