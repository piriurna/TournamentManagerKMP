package com.piriurna.tournamentmanager.android.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import dev.gitlive.firebase.auth.FirebaseUser

@Composable
fun LoginPage(
    viewModel: LoginViewModel,
    onAuthSuccess : (FirebaseUser?) -> Unit
) {
    val uiState = viewModel.uiState.value
    
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        TextField(value = uiState.email, onValueChange = viewModel::onEmailChange)
        TextField(value = uiState.password, onValueChange = viewModel::onPasswordChange, visualTransformation = PasswordVisualTransformation())
        Button(onClick = {
            viewModel.onAuthenticate(onAuthSuccess)
        }) {
            Text(text = "Login With user and password")
        }
        
        
    }
}