package com.seminario.bovintrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.seminario.bovintrack.domain.useCase.PotreroUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PotreroViewModel @Inject constructor(
    private val potreroUseCase: PotreroUseCase
) : ViewModel() {

}