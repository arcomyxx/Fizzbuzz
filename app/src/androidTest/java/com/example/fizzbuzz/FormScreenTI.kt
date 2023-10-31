package com.example.fizzbuzz

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.fizzbuzz.domain.usecases.FizzBuzzUseCase
import com.example.fizzbuzz.presentation.ui.FormScreen
import com.example.fizzbuzz.presentation.viewmodels.ViewModel
import org.junit.Rule
import org.junit.Test

class FormScreenTI {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testValidCase() {
        composeTestRule.setContent {
            FormScreen(viewModel = ViewModel(FizzBuzzUseCase()))
        }

        composeTestRule.onNodeWithTag("int1TextField")
            .assertIsDisplayed()
            .assert(hasText(""))
        composeTestRule.onNodeWithTag("int2TextField")
            .assertIsDisplayed()
            .assert(hasText(""))
        composeTestRule.onNodeWithTag("word1TextField")
            .assertIsDisplayed()
            .assert(hasText(""))
        composeTestRule.onNodeWithTag("word2TextField")
            .assertIsDisplayed()
            .assert(hasText(""))
        composeTestRule.onNodeWithTag("limitTextField")
            .assertIsDisplayed()
            .assert(hasText(""))

        composeTestRule.onNodeWithTag("int1TextField")
            .performTextInput("3")
        composeTestRule.onNodeWithTag("int2TextField")
            .performTextInput("5")
        composeTestRule.onNodeWithTag("word1TextField")
            .performTextInput("Fizz")
        composeTestRule.onNodeWithTag("word2TextField")
            .performTextInput("Buzz")
        composeTestRule.onNodeWithTag("limitTextField")
            .performTextInput("15")

        composeTestRule.onNodeWithTag("int1TextField")
            .assert(hasText("3"))
        composeTestRule.onNodeWithTag("int2TextField")
            .assert(hasText("5"))
        composeTestRule.onNodeWithTag("word1TextField")
            .assert(hasText("Fizz"))
        composeTestRule.onNodeWithTag("word2TextField")
            .assert(hasText("Buzz"))
        composeTestRule.onNodeWithTag("limitTextField")
            .assert(hasText("15"))
        composeTestRule.onNodeWithTag("errorField")
            .assertDoesNotExist()

        composeTestRule.onNodeWithTag("playButton")
            .assertIsDisplayed()
            .assert(hasText("JOUER"))
    }

    @Test
    fun testErrorCase() {
        composeTestRule.setContent {
            FormScreen(viewModel = ViewModel(FizzBuzzUseCase()))
        }

        composeTestRule.onNodeWithTag("int1TextField")
            .assertIsDisplayed()
            .assert(hasText(""))
        composeTestRule.onNodeWithTag("int2TextField")
            .assertIsDisplayed()
            .assert(hasText(""))
        composeTestRule.onNodeWithTag("word1TextField")
            .assertIsDisplayed()
            .assert(hasText(""))
        composeTestRule.onNodeWithTag("word2TextField")
            .assertIsDisplayed()
            .assert(hasText(""))
        composeTestRule.onNodeWithTag("limitTextField")
            .assertIsDisplayed()
            .assert(hasText(""))

        composeTestRule.onNodeWithTag("int1TextField")
            .performTextInput("3.5")
        composeTestRule.onNodeWithTag("int2TextField")
            .performTextInput("5")
        composeTestRule.onNodeWithTag("word1TextField")
            .performTextInput("Fizz")
        composeTestRule.onNodeWithTag("word2TextField")
            .performTextInput("Buzz")
        composeTestRule.onNodeWithTag("limitTextField")
            .performTextInput("15")

        composeTestRule.onNodeWithTag("int1TextField")
            .assert(hasText("3.5"))
        composeTestRule.onNodeWithTag("int2TextField")
            .assert(hasText("5"))
        composeTestRule.onNodeWithTag("word1TextField")
            .assert(hasText("Fizz"))
        composeTestRule.onNodeWithTag("word2TextField")
            .assert(hasText("Buzz"))
        composeTestRule.onNodeWithTag("limitTextField")
            .assert(hasText("15"))
        composeTestRule.onNodeWithTag("errorField")
            .assertDoesNotExist()

        composeTestRule.onNodeWithTag("playButton")
            .assertIsDisplayed()
            .assert(hasText("JOUER"))
            .performClick()

        composeTestRule.onNodeWithTag("errorField")
            .assertExists()
            .assertIsDisplayed()
            .assert(hasText("entier 1 n'est pas un nombre"))
    }
}

