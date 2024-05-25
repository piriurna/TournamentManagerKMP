package com.piriurna.tournamentmanager.android.team.createteam.components

import androidx.lifecycle.viewModelScope
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.android.dashboard.navigation.DashboardDestination
import com.piriurna.tournamentmanager.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.domain.usecases.AppResult
import com.piriurna.tournamentmanager.domain.usecases.CreateTeamUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


data class CreateTeamUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val teamName: String = "",
    val teamImage: String = ""
): UiState

class CreateTeamViewModel(
    private val createTeamUseCase: CreateTeamUseCase
): BaseViewModel<CreateTeamUiState>() {
    override fun initialState() = CreateTeamUiState()


    fun updateTeamName(teamName: String) {
        updateUiState(uiState.value.copy(teamName = teamName))
    }

    fun updateImageUrl(imageUrl: String) {
        updateUiState(uiState.value.copy(teamImage = imageUrl))
    }

    fun createTeam() {
        viewModelScope.launch {
            createTeamUseCase(uiState.value.teamName, uiState.value.teamImage).collectLatest {
                when(it) {
                    is AppResult.Success -> {
                        navigateToDestination(DashboardDestination)
                    }

                    is AppResult.Loading -> {
                        updateUiState(uiState.value.copy(isLoading = true))
                    }

                    is AppResult.Error -> {
                        updateUiState(uiState.value.copy(isLoading = false, error = it.message))
                    }
                }
            }


        }
    }
}