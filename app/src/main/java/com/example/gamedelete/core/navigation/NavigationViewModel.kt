package com.example.gamedelete.core.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gamedelete.core.domain.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
 preferencesRepository: PreferencesRepository
) : ViewModel() {

    var startDestination by mutableStateOf<Destination>(Destination.Onboarding)
    private set

    init {
        startDestination = if (preferencesRepository.readOnboardingState()) Destination.Onboarding
            else Destination.Home
    }





}