package com.piriurna.tournamentmanager.android.login.components.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.piriurna.tournamentmanager.android.R

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
) {
    val uiState = viewModel.uiState.value
    
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        TextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text(text = stringResource(R.string.email)) }
        )

        TextField(
            value = uiState.email,
            onValueChange = viewModel::onNicknameChange,
            label = { Text(text = stringResource(R.string.nickname)) }
        )

        TextField(
            value = uiState.password,
            onValueChange = viewModel::onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = stringResource(R.string.password)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = viewModel::onAuthenticate) {
            Text(text = stringResource(R.string.register))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = "Already have an account?"
            )
            Text(
                modifier = Modifier.clickable(onClick = viewModel::goToLogin),
                text = "Login",
                color = MaterialTheme.colorScheme.primary
            )
        }

        if(uiState.error != null)
            Text(text = uiState.error)
        
        
    }
}