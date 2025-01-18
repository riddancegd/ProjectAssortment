package com.example.gamedelete.form_validation.domain.use_case

import com.example.gamedelete.form_validation.domain.Result
import javax.inject.Inject

class ValidateTermsUseCase @Inject constructor() {


    operator fun invoke(isAccepted : Boolean) : Result {

        return if (isAccepted) Result.Success else Result.Error("Accept Terms and Conditions")

    }

}