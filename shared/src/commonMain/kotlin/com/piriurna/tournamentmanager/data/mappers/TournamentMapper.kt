package com.piriurna.tournamentmanager.data.mappers

import com.piriurna.tournamentmanager.data.models.ApiTeam
import com.piriurna.tournamentmanager.data.models.ApiTournament
import com.piriurna.tournamentmanager.data.models.ApiUser
import com.piriurna.tournamentmanager.domain.models.Team
import com.piriurna.tournamentmanager.domain.models.Tournament
import com.piriurna.tournamentmanager.domain.models.User

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
    return Tournament(
        id = id,
        name = name,
        owner = owner.toUser(),
        matches = emptyList(),
        date = startDate,
        teams = assignedTeams.map { it.toTeam() },
        status = status
    )
}