package com.piriurna.tournamentmanager.android.login.components.login

import androidx.lifecycle.viewModelScope
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.android.login.navigation.LoginDestinations.RegisterDestination
import com.piriurna.tournamentmanager.common.domain.GlobalNavigator
import com.piriurna.tournamentmanager.login.domain.usecases.AppResult
import com.piriurna.tournamentmanager.login.domain.usecases.LoginUserUseCase
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val nickname: String = "",
    val password: String = "",
    val loggedInUser: FirebaseUser? = null,
    val error: String? = null
): UiState

class LoginViewModel(
    private val loginUserUseCase: LoginUserUseCase
): BaseViewModel<LoginUiState>() {

    override fun initialState() = LoginUiState()
    fun onAuthenticate() {
        viewModelScope.launch {
            loginUserUseCase(uiState.value.email, uiState.value.password).collectLatest {
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

    fun goToRegister() {
        navigateToDestination(RegisterDestination)
    }
}