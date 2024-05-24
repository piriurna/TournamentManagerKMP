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
import com.piriurna.tournamentmanager.android.navigation.AppNavGraph
import com.piriurna.tournamentmanager.data.services.FirebaseServiceImpl
import com.piriurna.tournamentmanager.domain.GlobalNavigationHandler
import com.piriurna.tournamentmanager.domain.GlobalNavigator
import dev.gitlive.firebase.auth.FirebaseUser

class MainActivity : ComponentActivity(), GlobalNavigationHandler {

    private lateinit var navController: NavHostController
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            val context = LocalContext.current
            viewModel = customViewModelFactory(navController = navController) {
                val firebaseService = (context.applicationContext as MyApplication).firebaseService
                MainViewModel(firebaseService, it)
            }
            GlobalNavigator.registerHandler(this)
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavGraph(
                        navController = navController,
                        appUiState = viewModel.uiState.value,
                        updateLoggedInUser = viewModel::onUserChange
                    )
                }
            }
        }
    }

    override fun logout(user: FirebaseUser?) {
        viewModel.onUserChange(user)
    }

    override fun login(user: FirebaseUser?) {
        viewModel.onUserChange(user)
    }

    override fun onStop() {
        super.onStop()
        GlobalNavigator.unregisterHandler()
    }
}
