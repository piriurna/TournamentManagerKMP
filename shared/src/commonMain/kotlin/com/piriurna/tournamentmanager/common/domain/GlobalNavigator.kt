package com.piriurna.tournamentmanager.common.domain

import com.piriurna.tournamentmanager.fifacups.domain.models.User

interface GlobalNavigationHandler {
    fun logout(user: User?)

    fun login(user: User?)
}

object GlobalNavigator {

    private var handler: GlobalNavigationHandler? = null

    fun registerHandler(handler: GlobalNavigationHandler) {
        GlobalNavigator.handler = handler
    }

    fun unregisterHandler() {
        handler = null
    }

    fun logout() {
        handler?.logout(null)
    }

    fun login(user: User?) {
        handler?.login(user)
    }
}