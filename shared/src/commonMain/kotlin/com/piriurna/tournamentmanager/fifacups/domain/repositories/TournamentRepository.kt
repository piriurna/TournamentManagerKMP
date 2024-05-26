package com.piriurna.tournamentmanager.fifacups.domain.repositories

import com.piriurna.tournamentmanager.fifacups.domain.models.Team
import com.piriurna.tournamentmanager.fifacups.domain.models.Tournament
import com.piriurna.tournamentmanager.fifacups.domain.models.User


interface TournamentRepository {



    //=======================
    // USER
    //=======================
    suspend fun registerUser(email: String, nickname: String): Result<User>

    suspend fun checkUser(): Result<User>

    //=======================
    // TEAM
    //=======================
    suspend fun createTeam(name: String, imageUrl: String): Result<Team>

    suspend fun getUserTeam(): Result<Team?>


    //=======================
    // Tournament
    //=======================
    suspend fun getTournamentsForUser(): Result<List<Tournament>>
}