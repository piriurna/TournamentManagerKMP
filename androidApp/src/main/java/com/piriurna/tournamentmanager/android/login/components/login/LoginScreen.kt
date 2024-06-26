package com.piriurna.tournamentmanager.android.login.components.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.piriurna.tournamentmanager.android.R
import com.piriurna.tournamentmanager.android.common.components.LoadingScreen

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
) {
    val uiState = viewModel.uiState.value
    if(uiState.isLoading) {
        LoadingScreen()
    } else {
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
                value = uiState.password,
                onValueChange = viewModel::onPasswordChange,
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = stringResource(R.string.password)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = viewModel::onAuthenticate) {
                Text(text = stringResource(R.string.login))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.clickable(onClick = viewModel::goToRegister),
                text = buildAnnotatedString {
                    append(stringResource(R.string.don_t_have_an_account))

                    append(" ")

                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(stringResource(id = R.string.register))
                    }
                }
            )

            if(uiState.error != null)
                Text(text = uiState.error)
        }
    }
}