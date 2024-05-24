package com.piriurna.tournamentmanager.android.user

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.android.dashboard.navigation.DashboardDestination
import com.piriurna.tournamentmanager.domain.usecases.AppResult
import com.piriurna.tournamentmanager.domain.usecases.CreateTeamUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class RegisterTeamUiState(
    val isLoading: Boolean = false,
    val name: String = "",
    val imageUrl: String = "",
    val error: String? = null
): UiState


class RegisterTeamViewModel(
    private val createTeamUseCase: CreateTeamUseCase,
    navController: NavController
): BaseViewModel<RegisterTeamUiState>(navController) {
    override fun initialState() = RegisterTeamUiState()

    fun registerTeam() {
        viewModelScope.launch {
            createTeamUseCase.invoke(uiState.value.name, uiState.value.imageUrl).collectLatest {
                when(it) {
                    is AppResult.Loading -> {
                        updateUiState(uiState.value.copy(isLoading = true))
                    }

                    is AppResult.Success -> {
                        updateUiState(uiState.value.copy(isLoading = false, error = null))
                        navigateToDestination(DashboardDestination)
                    }

                    is AppResult.Error -> {
                        updateUiState(uiState.value.copy(isLoading = false, error = it.message))
                    }
                }
            }
        }
    }

    fun onNameChange(name: String) {
        updateUiState(uiState.value.copy(name = name))
    }

    fun onImageUrlChange(imageUrl: String) {
        updateUiState(uiState.value.copy(imageUrl = imageUrl))
    }


}