package com.piriurna.tournamentmanager.android.login.components

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.domain.GlobalNavigator
import com.piriurna.tournamentmanager.domain.services.FirebaseService
import com.piriurna.tournamentmanager.domain.usecases.AppResult
import com.piriurna.tournamentmanager.domain.usecases.AuthenticateUserUseCase
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val loggedInUser: FirebaseUser? = null,
    val error: String? = null
): UiState

class LoginViewModel(
    private val authenticateUserUseCase: AuthenticateUserUseCase
): BaseViewModel<LoginUiState>() {

    override fun initialState() = LoginUiState()
    fun onAuthenticate() {
        viewModelScope.launch {
            authenticateUserUseCase(uiState.value.email, uiState.value.password).collectLatest {
                when(it) {
                    is AppResult.Success -> GlobalNavigator.login(it.data)

                    is AppResult.Loading -> {}

                    is AppResult.Error -> {updateUiState(uiState.value.copy(error= it.message))}
                }
            }
        }
    }

    fun onEmailChange(email: String) {
        updateUiState(
            uiState.value.copy(
                email = email
            )
        )
    }

    fun onPasswordChange(password: String) {
        updateUiState(
            uiState.value.copy(
                password = password
            )
        )
    }
}