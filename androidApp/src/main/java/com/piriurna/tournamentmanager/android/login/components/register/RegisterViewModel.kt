package com.piriurna.tournamentmanager.android.login.components.register

import androidx.lifecycle.viewModelScope
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.android.login.navigation.LoginDestinations.LoginDestination
import com.piriurna.tournamentmanager.common.domain.GlobalNavigator
import com.piriurna.tournamentmanager.login.domain.usecases.AppResult
import com.piriurna.tournamentmanager.login.domain.usecases.RegisterUserUseCase
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class RegisterUiState(
    val email: String = "",
    val nickname: String = "",
    val password: String = "",
    val loggedInUser: FirebaseUser? = null,
    val error: String? = null
): UiState

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase
): BaseViewModel<RegisterUiState>() {

    override fun initialState() = RegisterUiState()
    fun onAuthenticate() {
        viewModelScope.launch {
            registerUserUseCase(uiState.value.email, uiState.value.nickname, uiState.value.password).collectLatest {
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
    fun onNicknameChange(nickname: String) {
        updateUiState(
            uiState.value.copy(
                nickname = nickname
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

    fun goToLogin() {
        navigateToDestination(LoginDestination)
    }
}