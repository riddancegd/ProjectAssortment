package com.example.gamedelete.form_validation.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamedelete.ui.theme.GameDeleteTheme


@Composable
fun FormValidationRoot(
    viewModel: FormValidationViewModel
) {
    FormValidationScreen(
        viewModel.state,
        viewModel::usernameChange,
        viewModel::passwordChange,
        viewModel::repeatedPasswordChange,
        viewModel::termsAcceptedChange
    )
}


@Composable
fun FormValidationScreen(
    state: FormValidationState,
    onUserNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatedPasswordChange: (String) -> Unit,
    onTermsAccepted: (Boolean) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        //horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = state.email,
            onValueChange = onUserNameChange,
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )

        state.emailError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }

        OutlinedTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password Icon")
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        state.passwordError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }

        OutlinedTextField(
            value = state.repeatedPassword,
            onValueChange = onRepeatedPasswordChange,
            label = { Text("Repeat Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        state.repeatedPasswordError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = state.termsAccepted,
                onCheckedChange = onTermsAccepted,

                )

            Text("Accept Terms", color = Color.Blue)
        }

        state.termsAcceptedError?.let { Text(
            text = it,
            color = MaterialTheme.colorScheme.error
        ) }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            Button(onClick = {}) {
                Text("Submit")
            }
        }


    }

}


@Preview()
@Composable
private fun FormValidationScreenPreview() {
    GameDeleteTheme {
        FormValidationScreen(
            FormValidationState(
                emailError = "Can't be empty",
                passwordError = "Please enter at least 8 characters",
                repeatedPasswordError = "Passwords do not match",
                termsAcceptedError = "Please accept the terms"
            ),
            onUserNameChange = {},
            onPasswordChange = {},
            onTermsAccepted = {},
            onRepeatedPasswordChange = {}
        )
    }
}