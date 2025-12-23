package com.example.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

/*
UI Tests tests actual instance of the app and its UI so the UI content must be set similar to how the content is set in
the onCreate() method int he MainActivity.
In this case, you need to write instructions to interact with the UI components so that the tip calculating process is tested through the UI

The @get:Rule annotation tells JUnit (the testing framework) that the composeTestRule property should be treated as a Test Rule.
This specific rule sets up the Jetpack Compose environment before every test runs and tears it down afterward, allowing you to interact
with your UI components (like finding text fields or buttons) in your tests.
The @get is a Use-site Target: Explicitly tells the Kotlin compiler to apply the @Rule annotation to the property's getter method
so that the JUnit framework can detect it.

createComposeRule() provides the core API for testing Compose UI components.
It gives you the power to:
•Set Content: Load your Composable functions (like TipTimeLayout) into the test environment using composeTestRule.setContent { ... }.
•Find Nodes: Locate UI elements (e.g., composeTestRule.onNodeWithText(...)).
•Perform Actions: Simulate user input (e.g., .performTextInput(...), .performClick()).
•Assert: Check if the UI looks correct (e.g., .assertExists(), .assertTextEquals(...)).
 */

class TipUITests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20percent_tip(){
        // this function call sets the UI content of the composeTestRule to the TipTimeLayout composable.
        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }
        /* Now that the UI content is set up, we can write instructions to interact with the UI components.
        In this particular test, we are going to test that the app displays the correct tip value based on the bill amount and tip percentage inputs
       The UI components can be accessed as nodes through the composeTestRule.
       Using onNodeWithText() can access a node that contains a particular text.
       Using the performTextInput() can simulate typing in a text field.
       */
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("20")
        // Now that the TextField composables are populated, the tip displays the Text composable, we have to make sure that the
        // Text composable displays the correct tip with an assertion
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip")
            .assertExists()

    }
}