package com.example.gamedelete.form_validation.presentation


sealed interface FormValidationAction {

    data class EmailChange(val email : String) : FormValidationAction
    data class PasswordChange(val password : String) : FormValidationAction
    data class RepeatedPasswordChange(val repeatedPassword : String) : FormValidationAction
    data class TermsAcceptedChange(val termsAccepted: Boolean) : FormValidationAction
    data object Submit : FormValidationAction

}