package com.piriurna.tournamentmanager.tournament.data.api

import com.piriurna.tournamentmanager.client
import com.piriurna.tournamentmanager.tournament.data.ApiResult
import com.piriurna.tournamentmanager.tournament.data.models.ApiTeam
import com.piriurna.tournamentmanager.tournament.data.models.ApiUser
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse

class FifaCupsApiImpl: FifaCupsApi {

    override suspend fun registerUser(email: String, uid: String): ApiResult<ApiUser> {
        return try {
            ApiResult.Success(client.post("user/register").body())
        } catch (e: Exception) {
            ApiResult.Error("error registering user: ${e.message}", status = 500)
        }

    }

    override suspend fun registerTeam(name: String, imageUrl: String): ApiResult<ApiTeam> {
        return try {
            ApiResult.Success(client.post("team/create").body())
        } catch (e: Exception) {
            ApiResult.Error("error registering team: ${e.message}", status = 500)
        }
    }


}