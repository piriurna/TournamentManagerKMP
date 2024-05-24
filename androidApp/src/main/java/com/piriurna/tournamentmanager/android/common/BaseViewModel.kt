package com.piriurna.tournamentmanager.android.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlin.reflect.KClass

interface UiState
abstract class BaseViewModel<T>(
    private val navController: NavController
): ViewModel() where T: UiState{

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
    private val viewModelClass: KClass<out BaseViewModel<T>>,
    private val dependencies: (NavController) -> BaseViewModel<T>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModelClass.java)) {
            return dependencies(navController) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
inline fun <reified VM : BaseViewModel<T>, T : UiState> customViewModelFactory(
    navController: NavController,
    noinline creator: (NavController) -> VM
): VM {
    val factory = remember {
        BaseViewModelFactory(navController, VM::class, creator)
    }
    return viewModel(factory = factory)
}