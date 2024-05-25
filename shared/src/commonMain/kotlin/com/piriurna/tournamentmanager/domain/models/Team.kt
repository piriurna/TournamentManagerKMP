package com.piriurna.tournamentmanager.domain.models

data class Team(
    val id: String,
    val name: String,
    val players: List<Player>,
    val owner: Player
)