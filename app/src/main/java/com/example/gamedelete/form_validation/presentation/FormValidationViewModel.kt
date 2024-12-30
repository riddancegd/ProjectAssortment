package com.example.gamedelete.form_validation.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FormValidationViewModel : ViewModel() {

    var state by mutableStateOf(FormValidationState())
        private set


    fun usernameChange(username: String) {
        state = state.copy(email = username)
    }

    fun passwordChange(password: String) {
        state = state.copy(password = password)
    }

    fun repeatedPasswordChange(repeatedPassword: String) {
        state = state.copy(repeatedPassword = repeatedPassword)
    }

    fun termsAcceptedChange(isAccepted: Boolean) {
        state = state.copy(termsAccepted = isAccepted)
    }


}