package com.example.gamedelete.form_validation.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamedelete.form_validation.domain.Result
import com.example.gamedelete.form_validation.domain.getErrorMessage
import com.example.gamedelete.form_validation.domain.use_case.ValidateEmailUseCase
import com.example.gamedelete.form_validation.domain.use_case.ValidatePasswordUseCase
import com.example.gamedelete.form_validation.domain.use_case.ValidateRepeatedPasswordUseCase
import com.example.gamedelete.form_validation.domain.use_case.ValidateTermsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormValidationViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val validateTermsUseCase: ValidateTermsUseCase
) : ViewModel() {

    var state by mutableStateOf(FormValidationState())
        private set

    private val _events = Channel<FormValidationEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action : FormValidationAction){
        when(action){
            is FormValidationAction.EmailChange ->{
                state = state.copy( email = action.email )
            }
            is FormValidationAction.PasswordChange ->{
                state = state.copy(password = action.password)
            }
            is FormValidationAction.RepeatedPasswordChange ->{
                state = state.copy(repeatedPassword = action.repeatedPassword)
            }
            is FormValidationAction.TermsAcceptedChange ->{
                state = state.copy(termsAccepted = action.termsAccepted)
            }
            FormValidationAction.Submit -> submitForm()
        }
    }


    private fun submitForm() {

        val emailValidationResult = validateEmailUseCase(state.email)
        val passwordValidationResult = validatePasswordUseCase(state.password)
        val repeatedPasswordResult =
            validateRepeatedPasswordUseCase(state.password, state.repeatedPassword)
        val termsAcceptedResult = validateTermsUseCase(state.termsAccepted)

        state = state.copy(
            emailError = emailValidationResult.getErrorMessage(),
            passwordError = passwordValidationResult.getErrorMessage(),
            repeatedPasswordError = repeatedPasswordResult.getErrorMessage(),
            termsAcceptedError = termsAcceptedResult.getErrorMessage()
        )

        val hasError = listOf(
            emailValidationResult,
            passwordValidationResult,
            repeatedPasswordResult,
            termsAcceptedResult
        ).any { it is Result.Error }

        if (!hasError) {
            viewModelScope.launch {
                _events.send(FormValidationEvent.Success)
            }
        }

    }


}
