package com.seminario.bovintrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.seminario.bovintrack.data.preferences.TokenPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val dataStorage: TokenPreference,
) : ViewModel(
){

}