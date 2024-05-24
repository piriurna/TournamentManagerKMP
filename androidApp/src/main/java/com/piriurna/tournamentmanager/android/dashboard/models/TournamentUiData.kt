package com.piriurna.tournamentmanager.android.dashboard.models

import com.piriurna.tournamentmanager.domain.models.Match
import com.piriurna.tournamentmanager.domain.models.User

class TournamentInfoUiData(
    val name: String,
    val status: Match.MatchStatus,
    val registeredPlayers: List<User>,
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