package com.piriurna.tournamentmanager.android.dashboard.components

import androidx.lifecycle.viewModelScope
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.android.team.navigation.TeamNavigation.CreateTeamHomePageDestination
import com.piriurna.tournamentmanager.fifacups.domain.models.Team
import com.piriurna.tournamentmanager.fifacups.domain.models.Tournament
import com.piriurna.tournamentmanager.fifacups.domain.models.User
import com.piriurna.tournamentmanager.fifacups.domain.usecases.GetLoggedInUserUseCase
import com.piriurna.tournamentmanager.fifacups.domain.usecases.GetTournamentsUseCase
import com.piriurna.tournamentmanager.fifacups.domain.usecases.GetUserTeamListUseCase
import com.piriurna.tournamentmanager.login.domain.usecases.AppResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class DashboardUiState(
    val isLoading: Boolean = false,
    val tournaments: List<Tournament> = emptyList(),
    val teams: List<Team> = emptyList(),
    val loggedInUser: User? = null
): UiState


class DashboardViewModel(
    private val getTournamentsUseCase: GetTournamentsUseCase,
    private val getUserTeamListUseCase: GetUserTeamListUseCase,
    private val getLoggedInUserUseCase: GetLoggedInUserUseCase
): BaseViewModel<DashboardUiState>() {
    override fun initialState() = DashboardUiState()


    init {
        viewModelScope.launch {
            getUser()
            getNextTournament()
            getUserTeam()
        }
    }

    private suspend fun getUser() {
        viewModelScope.launch {
            getLoggedInUserUseCase().collectLatest {
                when(it) {
                    is AppResult.Loading -> {
                        updateUiState(uiState.value.copy(
                            isLoading = true
                        ))
                    }

                    is AppResult.Error -> {
                        updateUiState(uiState.value.copy(
                            isLoading = false
                        ))
                    }

                    is AppResult.Success -> {
                        updateUiState(uiState.value.copy(
                            isLoading = false,
                            loggedInUser = it.data
                        ))
                    }
                }
            }
        }
    }

    private suspend fun getNextTournament() {
        getTournamentsUseCase().collectLatest {
            when(it) {
                is AppResult.Success -> {
                    updateUiState(
                        uiState.value.copy(
                            tournaments = it.data,
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
        getUserTeamListUseCase().collectLatest {
            when(it) {
                is AppResult.Success -> {
                    updateUiState(
                        uiState.value.copy(
                            teams = it.data,
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

    fun goToCreateTournamentPage() {
        navigateToDestination(CreateTeamHomePageDestination)
    }
}