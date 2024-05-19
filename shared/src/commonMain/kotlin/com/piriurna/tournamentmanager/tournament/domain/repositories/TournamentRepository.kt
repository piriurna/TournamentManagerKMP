package com.piriurna.tournamentmanager.tournament.domain.repositories

import com.piriurna.tournamentmanager.tournament.domain.models.Match
import com.piriurna.tournamentmanager.tournament.domain.models.Team
import com.piriurna.tournamentmanager.tournament.domain.models.Tournament
import com.piriurna.tournamentmanager.tournament.domain.models.User


interface TournamentRepository {

    //=======================
    // USER
    //=======================
    fun getUserInfo(id: String): User

    fun getUserTournaments(userId: String): List<Tournament>

    fun getUserTeams(userId: String): List<Team>

    fun getUserMatches(userId: String): List<Match>


    //===================
    // TOURNAMENT
    //===================
    fun getTournament(tournamentId: String): Tournament

    fun getTournamentMatches(tournamentId: String): List<Match>

    fun getTournamentTeams(tournamentId: String)


}