package com.piriurna.tournamentmanager.domain.repositories

import com.piriurna.tournamentmanager.domain.models.Team
import com.piriurna.tournamentmanager.domain.models.Tournament
import com.piriurna.tournamentmanager.domain.models.User


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