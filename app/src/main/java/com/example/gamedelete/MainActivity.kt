package com.example.gamedelete

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.gamedelete.AnimalGame.presentation.AnimalGameRoot
import com.example.gamedelete.AnimalGame.presentation.AnimalGameViewModel
import com.example.gamedelete.NameList.presentation.NameListRoot
import com.example.gamedelete.NameList.presentation.NameListViewModel
import com.example.gamedelete.form_validation.presentation.FormValidationRoot
import com.example.gamedelete.form_validation.presentation.FormValidationViewModel
import com.example.gamedelete.practice.presentation.LoginScreen
import com.example.gamedelete.ui.theme.GameDeleteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<FormValidationViewModel>()

        setContent {
            GameDeleteTheme {

                //AnimalGameRoot(viewModel)
                //LoginScreen()
                FormValidationRoot(viewModel)

            }
        }
    }
}
