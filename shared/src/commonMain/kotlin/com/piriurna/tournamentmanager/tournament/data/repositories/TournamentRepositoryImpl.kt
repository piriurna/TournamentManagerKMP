package com.piriurna.tournamentmanager.tournament.data.repositories

import com.piriurna.tournamentmanager.tournament.domain.models.Match
import com.piriurna.tournamentmanager.tournament.domain.models.Team
import com.piriurna.tournamentmanager.tournament.domain.models.Tournament
import com.piriurna.tournamentmanager.tournament.domain.models.User
import com.piriurna.tournamentmanager.tournament.domain.repositories.TournamentRepository

class TournamentRepositoryImpl: TournamentRepository {
    override fun getUserInfo(id: String): User {
        TODO("Not yet implemented")
    }

    override fun getUserTournaments(userId: String): List<Tournament> {
        TODO("Not yet implemented")
    }

    override fun getUserTeams(userId: String): List<Team> {
        TODO("Not yet implemented")
    }

    override fun getUserMatches(userId: String): List<Match> {
        TODO("Not yet implemented")
    }

    override fun getTournament(tournamentId: String): Tournament {
        TODO("Not yet implemented")
    }

    override fun getTournamentMatches(tournamentId: String): List<Match> {
        TODO("Not yet implemented")
    }

    override fun getTournamentTeams(tournamentId: String) {
        TODO("Not yet implemented")
    }


}