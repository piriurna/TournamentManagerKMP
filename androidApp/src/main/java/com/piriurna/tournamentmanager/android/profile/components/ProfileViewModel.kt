package com.piriurna.tournamentmanager.android.profile.components

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.piriurna.tournamentmanager.android.common.BaseViewModel
import com.piriurna.tournamentmanager.android.common.UiState
import com.piriurna.tournamentmanager.domain.services.FirebaseService
import kotlinx.coroutines.launch

data class ProfileUiState(
    private val isLoading: Boolean = false
): UiState

class ProfileViewModel(
    private val firebaseService: FirebaseService,
    navController: NavController
): BaseViewModel<ProfileUiState>(navController) {

    override fun initialState() = ProfileUiState()
    fun logout() {
        viewModelScope.launch {
            firebaseService.logOutUser()
        }
    }
}