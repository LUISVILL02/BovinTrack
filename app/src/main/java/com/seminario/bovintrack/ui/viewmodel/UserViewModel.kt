package com.seminario.bovintrack.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seminario.bovintrack.data.dto.auth.User
import com.seminario.bovintrack.data.preferences.TokenPreference
import com.seminario.bovintrack.utils.TokenDecode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val dataStorage: TokenPreference,
    private val decode: TokenDecode
) : ViewModel(){

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loadUserFromToken() {
        viewModelScope.launch {
            dataStorage.tokenflow.collect { token ->
                if (token != null) {
                    val user = decode.decodeJwt(token)
                    _user.value = user
                } else {
                    Log.i("UserViewModel", "Token is null")
                    _user.value = null
                }
            }
        }
    }
}