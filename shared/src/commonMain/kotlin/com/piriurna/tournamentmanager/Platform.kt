package com.piriurna.tournamentmanager

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform