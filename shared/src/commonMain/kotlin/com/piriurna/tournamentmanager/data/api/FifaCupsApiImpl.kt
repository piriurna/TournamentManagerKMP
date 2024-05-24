package com.piriurna.tournamentmanager.data.api

import com.piriurna.tournamentmanager.data.client
import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.data.api.models.CreateTeamRequestBody
import com.piriurna.tournamentmanager.data.api.models.CreateUserRequestBody
import com.piriurna.tournamentmanager.data.bodyOrError
import com.piriurna.tournamentmanager.domain.services.FirebaseService
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.isSuccess

class FifaCupsApiImpl(
    private val firebaseService: FirebaseService
): FifaCupsApi {

    override suspend fun registerUser(createUserRequestBody: CreateUserRequestBody): ApiResult<String> { //TODO: TEMPORARY STRING RETURNING FROM API
        val authToken = firebaseService.getAuthToken()
            ?: return ApiResult.Error("No Auth Token", status = 401)

        return try {
            client
                .post {
                    url("user/register")
                    bearerAuth(authToken)
                    setBody(createUserRequestBody)
                }
                .bodyOrError<String>()
        } catch (e: Exception) {
            ApiResult.Error("error registering user: ${e.message}", status = 500)
        }
    }

    override suspend fun registerTeam(createTeamRequestBody: CreateTeamRequestBody): ApiResult<String> {
        val authToken = firebaseService.getAuthToken()
            ?: return ApiResult.Error("No Auth Token", status = 401)

        return try {
            client.post {
                url("team/create")
                bearerAuth(authToken)
                setBody(createTeamRequestBody)
            }
            .bodyOrError<String>()
        } catch (e: Exception) {
            ApiResult.Error("error registering team: ${e.message}", status = 500)
        }
    }
}