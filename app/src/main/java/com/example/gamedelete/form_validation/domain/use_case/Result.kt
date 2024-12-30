package com.example.gamedelete.form_validation.domain.use_case


sealed interface Result {

    data object Success : Result
    data class Error(val message : String) : Result

}