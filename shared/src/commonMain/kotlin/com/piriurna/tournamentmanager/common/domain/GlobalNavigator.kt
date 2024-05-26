package com.piriurna.tournamentmanager.common.domain

import dev.gitlive.firebase.auth.FirebaseUser

interface GlobalNavigationHandler {
    fun logout(user: FirebaseUser?)

    fun login(user: FirebaseUser?)
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

    fun login(user: FirebaseUser?) {
        handler?.login(user)
    }
}