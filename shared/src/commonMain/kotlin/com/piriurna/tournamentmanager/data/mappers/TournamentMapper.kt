package com.piriurna.tournamentmanager.data.mappers

import com.piriurna.tournamentmanager.data.models.ApiOwner
import com.piriurna.tournamentmanager.data.models.ApiTeam
import com.piriurna.tournamentmanager.data.models.ApiUser
import com.piriurna.tournamentmanager.domain.models.Owner
import com.piriurna.tournamentmanager.domain.models.Team
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
        owner.toOwner(),
    )
}

fun ApiOwner.toOwner(): Owner {
    return Owner(
        id, name
    )
}
