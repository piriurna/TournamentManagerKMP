package com.piriurna.tournamentmanager.fifacups.data.api

import com.piriurna.tournamentmanager.common.data.client
import com.piriurna.tournamentmanager.common.data.ApiResult
import com.piriurna.tournamentmanager.fifacups.data.api.models.CreateTeamRequestBody
import com.piriurna.tournamentmanager.fifacups.data.api.models.CreateUserRequestBody
import com.piriurna.tournamentmanager.fifacups.data.api.models.tournament.TournamentListResponse
import com.piriurna.tournamentmanager.common.data.bodyOrError
import com.piriurna.tournamentmanager.fifacups.data.models.ApiTeam
import com.piriurna.tournamentmanager.fifacups.data.models.ApiUser
import com.piriurna.tournamentmanager.fifacups.domain.repositories.FifaCupsApiError
import com.piriurna.tournamentmanager.firebase.domain.api.FirebaseApi
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url

class FifaCupsApiImpl(
    private val firebaseApi: FirebaseApi
): FifaCupsApi {

    override suspend fun registerUser(createUserRequestBody: CreateUserRequestBody): ApiResult<ApiUser> { //TODO: TEMPORARY STRING RETURNING FROM API
        val authToken = firebaseApi.getAuthToken()
            ?:  return FifaCupsApiError.NoAuthToken()

        return try {
            client
                .post {
                    url("user")
                    bearerAuth(authToken)
                    setBody(createUserRequestBody)
                }
                .bodyOrError<ApiUser>()
        } catch (e: Exception) {
            FifaCupsApiError.GenericError()
        }
    }

    override suspend fun checkUser(): ApiResult<ApiUser> {
        val authToken = firebaseApi.getAuthToken()
            ?: return FifaCupsApiError.NoAuthToken()

        return try {
            client
                .get {
                    url("user")
                    bearerAuth(authToken)
                }
                .bodyOrError<ApiUser>()
        } catch (e: Exception) {
            FifaCupsApiError.GenericError()
        }
    }

    override suspend fun registerTeam(createTeamRequestBody: CreateTeamRequestBody): ApiResult<ApiTeam> {
        val authToken = firebaseApi.getAuthToken()
            ?: return FifaCupsApiError.NoAuthToken()

        return try {
            client.post {
                url("team/create")
                bearerAuth(authToken)
                setBody(createTeamRequestBody)
            }
            .bodyOrError<ApiTeam>()
        } catch (e: Exception) {
            FifaCupsApiError.GenericError()
        }
    }

    override suspend fun getUserTeams(): ApiResult<List<ApiTeam>> {
        val authToken = firebaseApi.getAuthToken()
            ?: return FifaCupsApiError.NoAuthToken()

        return try {
            client.get {
                url("user/teams")
                bearerAuth(authToken)
            }
            .bodyOrError<List<ApiTeam>>()
        } catch (e: Exception) {
            FifaCupsApiError.GenericError()
        }
    }

    override suspend fun getTournamentsForUser(): ApiResult<TournamentListResponse> {
        val authToken = firebaseApi.getAuthToken()
            ?: return FifaCupsApiError.NoAuthToken()

        return try {
            client.get {
                url("tournament")
                bearerAuth(authToken)
            }
            .bodyOrError<TournamentListResponse>()
        } catch (e: Exception) {
            FifaCupsApiError.GenericError()
        }
    }
}