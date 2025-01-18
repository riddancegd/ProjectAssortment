package com.example.gamedelete

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.gamedelete.fruitgame.presentation.FruitGameViewModel
import com.example.gamedelete.delete_package.DeleteScreen
import com.example.gamedelete.delete_package.DeleteScreenRoot
import com.example.gamedelete.delete_package.DeleteViewModel
import com.example.gamedelete.ui.theme.GameDeleteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<DeleteViewModel>()

        setContent {
            GameDeleteTheme {

                //AnimalGameRoot(viewModel)
                //LoginScreen()
                //FormValidationRoot(viewModel)
                //FruitGameRoot(viewModel)
                //OnboardingScreenRoot()
                //NavigationWrapper()
                DeleteScreenRoot(viewModel)


            }
        }
    }
}
