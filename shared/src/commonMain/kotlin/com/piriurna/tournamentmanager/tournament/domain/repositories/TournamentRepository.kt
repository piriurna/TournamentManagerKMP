package com.piriurna.tournamentmanager.tournament.domain.repositories

import com.piriurna.tournamentmanager.tournament.data.models.ApiMatch
import com.piriurna.tournamentmanager.tournament.data.models.ApiTournament
import com.piriurna.tournamentmanager.tournament.data.models.ApiUser

interface TournamentRepository {

    fun getUserInfo(id: String): ApiUser

    fun getUserTournaments(userId: String): List<ApiTournament>

    fun getTournament(tournamentId: String): ApiTournament

    fun getTournamentMatches(tournamentId: String): List<ApiMatch>
}