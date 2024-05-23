package com.piriurna.tournamentmanager.android.login.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = ""
)

class LoginViewModel(
    private val firebaseAuth: FirebaseAuth?
): ViewModel() {

    private val _uiState = mutableStateOf(LoginUiState())
    val uiState: State<LoginUiState> = _uiState


    fun onAuthenticate() {
        viewModelScope.launch {
            val user = firebaseAuth?.currentUser
            if(user == null) {
                firebaseAuth
                    ?.createUserWithEmailAndPassword(uiState.value.email, uiState.value.password)
                    ?.addOnCompleteListener {
                        if(!it.isSuccessful) {
                            loginWithUser()
                        }
                    }
                    ?.addOnFailureListener {
                        loginWithUser()
                    }
            }
        }
    }

    private fun loginWithUser() {
        firebaseAuth
            ?.signInWithEmailAndPassword(uiState.value.email, uiState.value.password)
            ?.addOnCompleteListener {
                if (it.isSuccessful) {
                    // go to homepage
                }
            }
    }

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email
        )
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password
        )
    }
}