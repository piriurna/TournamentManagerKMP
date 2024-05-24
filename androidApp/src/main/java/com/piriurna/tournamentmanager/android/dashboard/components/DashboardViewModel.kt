package com.piriurna.tournamentmanager.android.dashboard.components

import androidx.navigation.NavController
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.android.createteam.navigation.CreateTeamNavigation.CreateTeamHomePageDestination


class DashboardViewModel(): BaseViewModel<UiState>() {
    override fun initialState(): UiState {
        return object : UiState {

        }
    }


    fun goToCreateTeamPage() {
        navigateToDestination(CreateTeamHomePageDestination)
    }
}