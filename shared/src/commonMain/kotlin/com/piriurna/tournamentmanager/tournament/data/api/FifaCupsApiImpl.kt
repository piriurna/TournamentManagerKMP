package com.piriurna.tournamentmanager.tournament.data.api

import com.piriurna.tournamentmanager.client
import com.piriurna.tournamentmanager.tournament.data.ApiResult
import com.piriurna.tournamentmanager.tournament.data.api.models.CreateTeamRequestBody
import com.piriurna.tournamentmanager.tournament.data.api.models.CreateUserRequestBody
import com.piriurna.tournamentmanager.tournament.data.models.ApiTeam
import com.piriurna.tournamentmanager.tournament.data.models.ApiUser
import com.piriurna.tournamentmanager.tournament.domain.services.FirebaseService
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url

class FifaCupsApiImpl(
    private val firebaseService: FirebaseService
): FifaCupsApi {

    override suspend fun registerUser(createUserRequestBody: CreateUserRequestBody): ApiResult<ApiUser> {
        val authToken = firebaseService.getAuthToken()
            ?: return ApiResult.Error("No Auth Token", status = 401)

        return try {
            ApiResult.Success(
                client
                    .post {
                        url("user/register")
                        bearerAuth(authToken)
                        setBody(createUserRequestBody)
                    }
                    .body()
            )
        } catch (e: Exception) {
            ApiResult.Error("error registering user: ${e.message}", status = 500)
        }

    }

    override suspend fun registerTeam(createTeamRequestBody: CreateTeamRequestBody): ApiResult<ApiTeam> {
        val authToken = firebaseService.getAuthToken()
            ?: return ApiResult.Error("No Auth Token", status = 401)

        return try {
            ApiResult.Success(
                client.post {
                    url("team/create")
                    bearerAuth(authToken)
                    setBody(createTeamRequestBody)
                }
                .body()
            )
        } catch (e: Exception) {
            ApiResult.Error("error registering team: ${e.message}", status = 500)
        }
    }


}