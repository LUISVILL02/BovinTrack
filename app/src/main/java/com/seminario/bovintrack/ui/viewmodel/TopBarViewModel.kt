package com.seminario.bovintrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seminario.bovintrack.data.preferences.TokenPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopBarViewModel @Inject constructor(
    private val dataStorage: TokenPreference,
) : ViewModel() {

    private val _token = MutableStateFlow(false)
    val isToken: StateFlow<Boolean> = _token

    init {
        viewModelScope.launch {
            dataStorage.tokenflow.collect { token ->
                _token.value = token != null
            }
        }
    }

}