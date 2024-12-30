package com.example.gamedelete.AnimalGame.presentation


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AnimalGameViewModelTest {

    // Use the StandardTestDispatcher for precise control
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        // Set the Main dispatcher to the test dispatcher
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Reset the Main dispatcher
        Dispatchers.resetMain()
    }

    @Test
    fun `given animal already typed, when checkInput is called, then show already typed message`() = runTest {
        // Given
        val viewModel = AnimalGameViewModel()
        viewModel.onTextChanged("Dog")
        viewModel.checkInput()
        viewModel.onTextChanged("Dog")

        // When
        viewModel.checkInput()

        // Advance time to let the delay complete
        advanceTimeBy(3000)

        // Then
        assertEquals("Animal already typed!", viewModel.state.message)
    }

    @Test
    fun `given animal in the list, when checkInput is called, then add score and reset animal`() = runTest {
        // Given
        val viewModel = AnimalGameViewModel()
        viewModel.onTextChanged("Dog")

        // When
        viewModel.checkInput()

        // Advance time to let the delay complete
        advanceTimeBy(3000)

        // Then
        assertEquals(10, viewModel.state.score)
        assertEquals("", viewModel.state.animal)
        assertEquals("+10 for you! :D", viewModel.state.message)
    }

    @Test
    fun `given animal not in the list, when checkInput is called, then show not in list message`() = runTest {
        // Given
        val viewModel = AnimalGameViewModel()
        viewModel.onTextChanged("Elephant")

        // When
        viewModel.checkInput()

        // Advance time to let the delay complete
        advanceTimeBy(3000)

        // Then
        assertEquals("Animal not in the list :(", viewModel.state.message)
        assertEquals("", viewModel.state.animal)
    }
}