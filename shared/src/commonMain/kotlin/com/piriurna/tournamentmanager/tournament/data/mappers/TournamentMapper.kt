package com.piriurna.tournamentmanager.tournament.data.mappers

import com.piriurna.tournamentmanager.tournament.data.models.ApiOwner
import com.piriurna.tournamentmanager.tournament.data.models.ApiTeam
import com.piriurna.tournamentmanager.tournament.data.models.ApiUser
import com.piriurna.tournamentmanager.tournament.domain.models.Owner
import com.piriurna.tournamentmanager.tournament.domain.models.Team
import com.piriurna.tournamentmanager.tournament.domain.models.User

fun ApiUser.toUser(): User {
    return User(
        id, nickname, email
    )
}

fun ApiTeam.toTeam(): Team {
    return Team(
        id,
        name,
        owner.toOwner(),
    )
}

fun ApiOwner.toOwner(): Owner {
    return Owner(
        id, name
    )
}
