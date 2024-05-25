package com.piriurna.tournamentmanager.android.dashboard.models

import com.piriurna.tournamentmanager.domain.models.Match
import com.piriurna.tournamentmanager.domain.models.Team
import com.piriurna.tournamentmanager.domain.models.Tournament
import com.piriurna.tournamentmanager.domain.models.User

class TournamentInfoUiData(
    val name: String,
    val status: Match.MatchStatus,
    val registeredTeams: List<Team>,
    val tournamentDate: String
) {

    val buttonText = when(status) {
        Match.MatchStatus.LIVE -> "Join"
        Match.MatchStatus.PAST -> ""
        Match.MatchStatus.PRESENT -> "See more info"
    }

    val statusText = when(status) {
        Match.MatchStatus.LIVE -> "Live"
        Match.MatchStatus.PAST -> "Ended"
        Match.MatchStatus.PRESENT -> tournamentDate
    }
}


fun Tournament.toUiData(): TournamentInfoUiData {
    return TournamentInfoUiData(
        name = name,
        status = matches.firstOrNull()?.status?:Match.MatchStatus.PRESENT, // TODO: FIX
        tournamentDate = "04-05-2000", // TODO: FIX
        registeredTeams = teams
    )
}