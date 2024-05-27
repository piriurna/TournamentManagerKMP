package com.piriurna.tournamentmanager.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.piriurna.tournamentmanager.android.common.customViewModelFactory
import com.piriurna.tournamentmanager.android.navigation.components.AppNavGraph
import com.piriurna.tournamentmanager.common.domain.GlobalNavigationHandler
import com.piriurna.tournamentmanager.common.domain.GlobalNavigator
import com.piriurna.tournamentmanager.fifacups.domain.models.User

class MainActivity : ComponentActivity(), GlobalNavigationHandler {

    private lateinit var navController: NavHostController
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            val context = LocalContext.current
            viewModel = customViewModelFactory(navController = navController) {
                MainViewModel((context.applicationContext as MyApplication).getLoggedInUserUseCase)
            }
            GlobalNavigator.registerHandler(this)
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavGraph(
                        navController = navController,
                        appUiState = viewModel.uiState.value
                    )
                }
            }
        }
    }

    override fun logout(user: User?) {
        viewModel.onUserChange(user)
    }

    override fun login(user: User?) {
        viewModel.onUserChange(user)
    }

    override fun onStop() {
        super.onStop()
        GlobalNavigator.unregisterHandler()
    }
}
