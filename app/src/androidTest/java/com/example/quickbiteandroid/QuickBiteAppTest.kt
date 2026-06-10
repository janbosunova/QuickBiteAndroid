package com.example.quickbiteandroid

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class QuickBiteAppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun viewMenuButtonNavigatesToMenuScreen() {

        composeTestRule.setContent {
            QuickBiteApp()
        }

        composeTestRule
            .onNodeWithText("View Menu")
            .performClick()

        composeTestRule
            .onNodeWithText("Menu")
            .assertIsDisplayed()
    }
}