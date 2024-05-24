package com.piriurna.tournamentmanager.android.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlin.reflect.KClass

interface UiState
abstract class BaseViewModel<T>(
): ViewModel() where T: UiState{
    private lateinit var navController: NavController

    fun initializeNavController(navController: NavController) {
        this.navController = navController
    }
    abstract fun initialState(): T

    private val _uiState = mutableStateOf(this.initialState())
    val uiState: State<T> = _uiState


    protected fun updateUiState(uiState: T) {
        _uiState.value = uiState
    }


    protected fun navigateToDestination(destinationUrl: String) {
        navController.navigate(destinationUrl)
    }
}


class BaseViewModelFactory<T : UiState>(
    private val navController: NavController,
    private val creator: () -> BaseViewModel<T>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
        val viewModel = creator.invoke()
        viewModel.initializeNavController(navController)

        return viewModel as VM
    }
}

@Composable
inline fun <reified VM : BaseViewModel<T>, T : UiState> customViewModelFactory(
    navController: NavController,
    noinline creator: () -> VM
): VM {
    val factory = remember {
        BaseViewModelFactory(navController, creator)
    }
    return viewModel(factory = factory)
}