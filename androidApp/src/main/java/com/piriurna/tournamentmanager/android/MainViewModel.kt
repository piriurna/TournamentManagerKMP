package com.piriurna.tournamentmanager.android

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.domain.services.FirebaseService
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch


data class AppUiState(
    val loggedInUser: FirebaseUser? = null
): UiState

class MainViewModel(
    private val firebaseService: FirebaseService,
    navController: NavController
): BaseViewModel<AppUiState>(navController) {
    override fun initialState() = AppUiState()

    init {
        getLoggedInUser()
    }
    private fun getLoggedInUser() {
        viewModelScope.launch {
            updateUiState(
                uiState.value.copy(
                    loggedInUser = firebaseService.getLoggedInUser()
                )
            )
        }
    }

    fun onUserChange(user: FirebaseUser?) {
        updateUiState(
            uiState.value.copy(
                loggedInUser = user
            )
        )
    }
}