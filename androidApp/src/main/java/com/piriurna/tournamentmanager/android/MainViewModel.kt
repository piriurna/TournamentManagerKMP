package com.piriurna.tournamentmanager.android

import androidx.lifecycle.viewModelScope
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.firebase.domain.api.FirebaseApi
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch


data class AppUiState(
    val loggedInUser: FirebaseUser? = null
): UiState

class MainViewModel(
    private val firebaseApi: FirebaseApi
): BaseViewModel<AppUiState>() {
    override fun initialState() = AppUiState()

    init {
        getLoggedInUser()
    }
    private fun getLoggedInUser() {
        viewModelScope.launch {
            updateUiState(
                uiState.value.copy(
                    loggedInUser = firebaseApi.getLoggedInUser()
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