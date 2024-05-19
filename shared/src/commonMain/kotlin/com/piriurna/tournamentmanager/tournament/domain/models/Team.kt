package com.piriurna.tournamentmanager.tournament.domain.models

data class Team(
    val id: String,
    val name: String,
    val owner: User
)