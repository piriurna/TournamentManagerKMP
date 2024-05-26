package com.piriurna.tournamentmanager.fifacups.data.mappers

import com.piriurna.tournamentmanager.fifacups.data.models.ApiMatch
import com.piriurna.tournamentmanager.fifacups.data.models.ApiTeam
import com.piriurna.tournamentmanager.fifacups.data.models.ApiTournament
import com.piriurna.tournamentmanager.fifacups.data.models.ApiTournamentGroup
import com.piriurna.tournamentmanager.fifacups.data.models.ApiUser
import com.piriurna.tournamentmanager.fifacups.domain.models.Match
import com.piriurna.tournamentmanager.fifacups.domain.models.Team
import com.piriurna.tournamentmanager.fifacups.domain.models.Tournament
import com.piriurna.tournamentmanager.fifacups.domain.models.TournamentGroup
import com.piriurna.tournamentmanager.fifacups.domain.models.User

fun ApiTeam.toTeam(): Team {
    return Team(
        id = id,
        name = name,
        players = players.map{ it.toUser() },
        owner = owner.toUser(),
        imageUrl = imageUrl?:""
    )
}

fun ApiUser.toUser(): User {
    return User(
        id = id,
        nickname = nickname,
        email = email
    )
}

fun ApiTournament.toTournament(): Tournament {
    val tournament =  Tournament(
        id = id,
        name = name,
        owner = owner.toUser(),
        matches = emptyList(),
        date = startDate,
        teams = assignedTeams.map { it.toTeam() },
        status = status,
        groups = emptyList()
    )

    return tournament.copy(
        groups = groups.map { it.toGroup(tournament) }
    )
}

fun ApiTournamentGroup.toGroup(tournament: Tournament): TournamentGroup {
    return TournamentGroup(
        id = id,
        tournament = tournament,
        table = table.map { it.toGroupTeamInfo() },
        matches = groupMatches.map { it.toMatch() }
    )
}


fun ApiTournamentGroup.ApiTeamGroupInfo.toGroupTeamInfo(): TournamentGroup.GroupTeamInfo {
    return TournamentGroup.GroupTeamInfo(
        team = team.toTeam(),
        points = points,
        goals = goals,
        goalsTaken = goalsTaken,
        matchesPlayed = playedMatches
    )
}


fun ApiMatch.toMatch(): Match {
    val homeTeam = homeApiTeam.toTeam()
    val awayTeam = awayApiTeam.toTeam()
    return Match(
        id = id,
        status = Match.MatchStatus.valueOf(status.name),
        awayTeam = Match.MatchTeam(awayTeam, score = awayGoals),
        homeTeam = Match.MatchTeam(homeTeam, score = homeGoals),
        winner = if(winnerId == homeApiTeam.id) homeTeam else if(winnerId == awayApiTeam.id) awayTeam else null,
    )
}