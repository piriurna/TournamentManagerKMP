package com.piriurna.tournamentmanager.android.profile.components

import androidx.lifecycle.viewModelScope
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.firebase.domain.api.FirebaseApi
import kotlinx.coroutines.launch

data class ProfileUiState(
    private val isLoading: Boolean = false
): UiState

class ProfileViewModel(
    private val firebaseApi: FirebaseApi
): BaseViewModel<ProfileUiState>() {

    override fun initialState() = ProfileUiState()
    fun logout() {
        viewModelScope.launch {
            firebaseApi.logOutUser()
        }
    }
}