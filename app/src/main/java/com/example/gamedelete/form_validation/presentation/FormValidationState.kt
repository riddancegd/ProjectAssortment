package com.example.gamedelete.form_validation.presentation

data class FormValidationState(

    val email : String = "",
    val emailError : String? = null,
    val password : String = "",
    val passwordError : String? = null,
    val repeatedPassword : String = "",
    val repeatedPasswordError : String? = null,
    val termsAccepted : Boolean = false,
    val termsAcceptedError : String? = null

)