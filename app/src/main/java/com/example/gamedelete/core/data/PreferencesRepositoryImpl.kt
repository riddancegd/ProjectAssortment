package com.example.gamedelete.core.data

import com.example.gamedelete.core.domain.PreferencesRepository

class PreferencesRepositoryImpl : PreferencesRepository {
    //Change this to see onboarding at startup
    override fun readOnboardingState() = false
}