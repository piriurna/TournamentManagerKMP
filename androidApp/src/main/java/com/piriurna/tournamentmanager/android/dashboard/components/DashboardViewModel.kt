package com.piriurna.tournamentmanager.android.dashboard.components

import androidx.lifecycle.viewModelScope
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.android.team.navigation.TeamNavigation.CreateTeamHomePageDestination
import com.piriurna.tournamentmanager.fifacups.domain.models.Team
import com.piriurna.tournamentmanager.fifacups.domain.models.Tournament
import com.piriurna.tournamentmanager.fifacups.domain.models.User
import com.piriurna.tournamentmanager.login.domain.usecases.AppResult
import com.piriurna.tournamentmanager.fifacups.domain.usecases.GetNextTournamentForUserUseCase
import com.piriurna.tournamentmanager.fifacups.domain.usecases.GetUserTeamUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class DashboardUiState(
    val isLoading: Boolean = false,
    val nextTournament: Tournament? = null,
    val myTeam: Team? = null,
    val loggedInUser: User? = null
): UiState


class DashboardViewModel(
    private val getNextTournamentForUserUseCase: GetNextTournamentForUserUseCase,
    private val getUserTeamUseCase: GetUserTeamUseCase,
): BaseViewModel<DashboardUiState>() {
    override fun initialState() = DashboardUiState()


    init {
        viewModelScope.launch {
            getNextTournament()
            getUserTeam()
        }
    }

    private suspend fun getNextTournament() {
        getNextTournamentForUserUseCase().collectLatest {
            when(it) {
                is AppResult.Success -> {
                    updateUiState(
                        uiState.value.copy(
                            nextTournament = it.data,
                            isLoading = false
                        )
                    )
                }

                is AppResult.Loading -> {
                    updateUiState(
                        uiState.value.copy(
                            isLoading = true
                        )
                    )
                }

                is AppResult.Error -> {
                    updateUiState(
                        uiState.value.copy(
                            isLoading = false
                        )
                    )
                }
            }
        }
    }

    private suspend fun getUserTeam() {
        getUserTeamUseCase().collectLatest {
            when(it) {
                is AppResult.Success -> {
                    updateUiState(
                        uiState.value.copy(
                            myTeam = it.data,
                            isLoading = false
                        )
                    )
                }

                is AppResult.Loading -> {
                    updateUiState(
                        uiState.value.copy(
                            isLoading = true
                        )
                    )
                }

                is AppResult.Error -> {
                    updateUiState(
                        uiState.value.copy(
                            isLoading = false
                        )
                    )
                }
            }
        }
    }

    fun goToCreateTeamPage() {
        navigateToDestination(CreateTeamHomePageDestination)
    }
}