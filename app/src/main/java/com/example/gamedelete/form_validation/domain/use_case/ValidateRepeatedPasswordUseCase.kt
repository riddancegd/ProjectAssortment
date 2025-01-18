package com.example.gamedelete.form_validation.domain.use_case

import com.example.gamedelete.form_validation.domain.Result
import javax.inject.Inject

class ValidateRepeatedPasswordUseCase @Inject constructor() {

    operator fun invoke(password: String, repeatedPassword: String): Result {

        return if(password != repeatedPassword) Result.Error("Repeated password doesn't match")
            else Result.Success

    }

}