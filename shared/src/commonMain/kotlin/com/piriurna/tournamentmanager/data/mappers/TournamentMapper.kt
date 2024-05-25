package com.piriurna.tournamentmanager.data.mappers

import com.piriurna.tournamentmanager.data.models.ApiPlayer
import com.piriurna.tournamentmanager.data.models.ApiTeam
import com.piriurna.tournamentmanager.data.models.ApiTournament
import com.piriurna.tournamentmanager.data.models.ApiUser
import com.piriurna.tournamentmanager.domain.models.Player
import com.piriurna.tournamentmanager.domain.models.Team
import com.piriurna.tournamentmanager.domain.models.Tournament
import com.piriurna.tournamentmanager.domain.models.User

fun ApiUser.toUser(): User {
    return User(
        id, nickname, email
    )
}

fun ApiTeam.toTeam(): Team {
    return Team(
        id,
        name,
        players.map{ it.toPlayer() },
        owner.toPlayer(),
    )
}

fun ApiPlayer.toPlayer(): Player {
    return Player(
        id, name
    )
}

fun ApiTournament.toTournament(): Tournament {
    return Tournament(
        id = id,
        name = name,
        owner = owner.toPlayer(),
        matches = emptyList(),
        date = startDate,
        teams = assignedTeams.map { it.toTeam() },
        status = status
    )
}