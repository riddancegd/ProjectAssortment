package com.example.gamedelete.form_validation.domain


sealed interface Result {

    data object Success : Result
    data class Error(val message : String) : Result

}

fun Result.getErrorMessage() : String? {
    return if (this is Result.Error) this.message else null
}