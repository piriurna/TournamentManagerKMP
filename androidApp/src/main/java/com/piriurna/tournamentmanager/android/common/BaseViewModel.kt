package com.piriurna.tournamentmanager.android.common

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

interface UiState
abstract class BaseViewModel<T>: ViewModel() where T: UiState{

    abstract fun initialState(): T

    private val _uiState = mutableStateOf(this.initialState())
    val uiState: State<T> = _uiState


    protected fun updateUiState(uiState: T) {
        _uiState.value = uiState
    }
}