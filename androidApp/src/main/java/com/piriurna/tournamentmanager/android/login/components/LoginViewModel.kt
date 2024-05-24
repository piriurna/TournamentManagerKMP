package com.piriurna.tournamentmanager.android.login.components

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.domain.GlobalNavigator
import com.piriurna.tournamentmanager.domain.services.FirebaseService
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val loggedInUser: FirebaseUser? = null
): UiState

class LoginViewModel(
    private val firebaseService: FirebaseService,
    navController: NavController
): BaseViewModel<LoginUiState>(navController) {

    override fun initialState() = LoginUiState()
    fun onAuthenticate(onAuthSuccess: (FirebaseUser?) -> Unit) {
        viewModelScope.launch {
            if(firebaseService.getLoggedInUser() == null) {
                GlobalNavigator.login(
                    firebaseService.authenticateUser(
                        uiState.value.email,
                        uiState.value.password
                    )?.user
                )
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