package com.piriurna.tournamentmanager.fifacups.domain.models

data class Team(
    val id: String,
    val name: String,
    val players: List<User>,
    val owner: User,
    val imageUrl: String
)