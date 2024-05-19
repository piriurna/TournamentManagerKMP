package com.piriurna.tournamentmanager.tournament.data.models

data class ApiTournament(
    val id: String,
    val name: String,
    val owner: ApiUser
)