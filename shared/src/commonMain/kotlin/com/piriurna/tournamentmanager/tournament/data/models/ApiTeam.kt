package com.piriurna.tournamentmanager.tournament.data.models

data class ApiTeam(
    val id: String,
    val name: String,
    val owner: ApiOwner
)