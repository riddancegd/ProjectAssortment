package com.example.gamedelete.fruitgame.presentation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
@OptIn(ExperimentalCoroutinesApi::class)
class FruitGameScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun scoreIsDisplayedCorrectly() {
        // Given
        val state = FruitGameState(score = 20)

        // When
        composeTestRule.setContent {
            ScoreText(score = state.score)
        }

        // Then
        composeTestRule.onNodeWithText("Score: 20").assertExists()
    }

    @Test
    fun fruitInputUpdatesCorrectly() {
        // Given
        var capturedInput = ""

        // When
        composeTestRule.setContent {
            FruitInput(
                fruitName = "",
                onValueChange = { capturedInput = it }
            )
        }

        // Then
        composeTestRule.onNodeWithText("Fruit").assertExists()
        composeTestRule.onNodeWithTag("fruitInput").performTextInput("apple")
        assertEquals("apple", capturedInput)
    }

    @Test
    fun submitButtonTriggersAction() {
        // Given
        var buttonClicked = false

        // When
        composeTestRule.setContent {
            SubmitButton(onClick = { buttonClicked = true })
        }

        // Then
        composeTestRule.onNodeWithText("Submit").performClick()
        assertTrue(buttonClicked)
    }

    @Test
    fun completeGameFlowWorksCorrectly() {
        // Given
        val state = FruitGameState()
        var capturedAction: FruitGameAction? = null
        val events = MutableSharedFlow<FruitGameEvent>()

        // When
        composeTestRule.setContent {
            FruitGameScreen(
                state = state,
                onAction = { capturedAction = it },
                events = events
            )
        }

        // Then
        composeTestRule.onNodeWithTag("fruitInput").performTextInput("apple")
        assertTrue(capturedAction is FruitGameAction.OnFruitNameChange)

        composeTestRule.onNodeWithText("Submit").performClick()
        assertTrue(capturedAction is FruitGameAction.SubmitFruit)


    }
}