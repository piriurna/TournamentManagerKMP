package com.piriurna.tournamentmanager.tournament.domain.repositories

import com.piriurna.tournamentmanager.tournament.data.ApiResult
import com.piriurna.tournamentmanager.tournament.domain.models.Match
import com.piriurna.tournamentmanager.tournament.domain.models.Team
import com.piriurna.tournamentmanager.tournament.domain.models.Tournament
import com.piriurna.tournamentmanager.tournament.domain.models.User


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