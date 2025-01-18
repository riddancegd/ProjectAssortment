package com.example.gamedelete.form_validation.presentation

sealed interface FormValidationEvent {

    data object Success : FormValidationEvent

}