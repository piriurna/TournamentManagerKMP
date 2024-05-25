package com.piriurna.tournamentmanager.android.tournament.components

import androidx.lifecycle.viewModelScope
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.domain.models.Tournament
import com.piriurna.tournamentmanager.domain.usecases.AppResult
import com.piriurna.tournamentmanager.domain.usecases.GetTournamentsByDateUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class TournamentsUiState(
    val isLoading: Boolean = false,
    val tournaments: Map<String, List<Tournament>> = emptyMap()
): UiState

class TournamentsViewModel(
    private val getTournamentsByDateUseCase: GetTournamentsByDateUseCase
): BaseViewModel<TournamentsUiState>() {
    override fun initialState() = TournamentsUiState()

    init {
        viewModelScope.launch {
            getTournamentsByDateUseCase().collectLatest {
                when(it) {
                    is AppResult.Success -> {
                        updateUiState(uiState.value.copy(
                            isLoading = false,
                            tournaments = it.data
                        ))
                    }

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
                }
            }
        }
    }
}