package com.piriurna.tournamentmanager.android

import androidx.lifecycle.viewModelScope
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.fifacups.domain.models.User
import com.piriurna.tournamentmanager.fifacups.domain.usecases.GetLoggedInUserUseCase
import com.piriurna.tournamentmanager.login.domain.usecases.AppResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


data class AppUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val loggedInUser: User? = null
): UiState

class MainViewModel(
    private val getLoggedInUserUseCase: GetLoggedInUserUseCase
): BaseViewModel<AppUiState>() {
    override fun initialState() = AppUiState()

    init {
        getLoggedInUser()
    }
    private fun getLoggedInUser() {
        viewModelScope.launch {
            getLoggedInUserUseCase().collectLatest {
                when(it) {
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
                                isLoading = false,
                            )
                        )
                    }

                    is AppResult.Success -> {
                        updateUiState(
                            uiState.value.copy(
                                loggedInUser = it.data
                            )
                        )
                    }
                }
            }

        }
    }

    fun onUserChange(user: User?) {
        updateUiState(
            uiState.value.copy(
                loggedInUser = user
            )
        )
    }
}