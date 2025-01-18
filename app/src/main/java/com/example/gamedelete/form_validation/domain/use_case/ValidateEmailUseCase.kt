package com.example.gamedelete.form_validation.domain.use_case

import android.util.Patterns
import com.example.gamedelete.form_validation.domain.Result
import javax.inject.Inject


class ValidateEmailUseCase @Inject constructor() {

    operator fun invoke(email: String) : Result {

        if (email.isBlank()) return Result.Error("Email can't be empty")


        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return Result.Error("Please enter a valid email address")


        return Result.Success

    }


}