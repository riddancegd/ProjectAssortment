package com.example.gamedelete.fruitgame.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FruitGameViewModelTest {

    private lateinit var viewModel: FruitGameViewModel
    private lateinit var testRepository: TestFruitRepository
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        testRepository = TestFruitRepository()
        viewModel = FruitGameViewModel(testRepository)

    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `when typing a fruit, state is updated`() {
        viewModel.onAction(FruitGameAction.OnFruitNameChange("apple"))
        assertEquals("apple", viewModel.state.fruitName)
    }

    @Test
    fun `when submitting a valid fruit, increase score and add fruit to the list`() {

        viewModel.onAction(FruitGameAction.OnFruitNameChange("apple"))

        viewModel.onAction(FruitGameAction.SubmitFruit)

        assertEquals(10, viewModel.state.score)
        assertEquals(listOf("apple"), viewModel.state.typedFruits)
        assertEquals("", viewModel.state.fruitName)
    }

    @Test
    fun `when an invalid fruit is submitted, don't update score nor add fruit to the list`() {

        viewModel.onAction(FruitGameAction.OnFruitNameChange("papaya"))

        viewModel.onAction(FruitGameAction.SubmitFruit)

        assertEquals(0, viewModel.state.score)
        assertEquals(listOf<String>(), viewModel.state.typedFruits)
        assertEquals("", viewModel.state.fruitName)

    }

    @Test
    fun `when a repeated fruit is submitted, nothing changes except input is cleared`() {

        viewModel.onAction(FruitGameAction.OnFruitNameChange("apple"))
        viewModel.onAction(FruitGameAction.SubmitFruit)

        viewModel.onAction(FruitGameAction.OnFruitNameChange("apple"))
        viewModel.onAction(FruitGameAction.SubmitFruit)

        assertEquals(10, viewModel.state.score)
        assertEquals(listOf("apple"), viewModel.state.typedFruits)
        assertEquals("", viewModel.state.fruitName)

    }


    @Test
    fun `when submitting a valid fruit, the correct toast is shown`() = runTest {

        //Create an event and set an initial event value
        var event: FruitGameEvent = FruitGameEvent.ShowToast("")

        //in a separate job, you set the collector. This is done before so that it can collect the
        // result when submit is executed. In a separate job so that you can cancel it at the end
        // of the test
        val job = launch {
            viewModel.events.collect { event = it }

        }

        //Do the process of inputting a good value
        viewModel.onAction(FruitGameAction.OnFruitNameChange("apple"))
        viewModel.onAction(FruitGameAction.SubmitFruit)
        //You advance and wait until the coroutine that emits the event is finished
        advanceUntilIdle()

        //Coroutine is finished here and our collector should have received the event, so you
        //check that the message of the event is the correct one.
        assertEquals("+10 pintztz :D", (event as FruitGameEvent.ShowToast).message)

        //You close the collector
        job.cancel()


    }


}