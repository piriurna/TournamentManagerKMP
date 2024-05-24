package com.piriurna.tournamentmanager.domain.repositories

import com.piriurna.tournamentmanager.data.ApiResult
import com.piriurna.tournamentmanager.domain.models.Match
import com.piriurna.tournamentmanager.domain.models.Team
import com.piriurna.tournamentmanager.domain.models.Tournament
import com.piriurna.tournamentmanager.domain.models.User


interface TournamentRepository {



    //=======================
    // TEAM
    //=======================
    suspend fun registerUser(email: String, nickname: String): Result<User>
    //=======================
    // TEAM
    //=======================
    suspend fun createTeam(name: String, imageUrl: String): Result<Team>


}