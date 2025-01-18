package com.example.gamedelete.form_validation.domain.use_case

import com.example.gamedelete.form_validation.domain.Result
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {

    operator fun invoke(password: String) : Result{

        if (password.isBlank()) return Result.Error("Password can't be empty")

        if (password.any { !it.isLetterOrDigit() }) return Result.Success

        return Result.Error("Password should contain at least one special character")
    }

}