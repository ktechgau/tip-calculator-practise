/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TipTimeLayout()
                }
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
    //State is any value that can change over time. When you want to change the or update that variable,  and display that, you rely on the recomposition process
    //State is immutable, whereas mutableStateOf() is mutable - the value can be changed.
    //Composable functions can store an object across recompositions with remember.
    //A value computed by remember is stored in Composition during initial composition and stored value is returned during recomposition
    var amountInput by remember {mutableStateOf("")}
    //.toDoubleOrNull is a predefined function that parses a string as a Double number and returns a result or null if the string is not a valid representation of a number
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount)
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            value = amountInput,
            // The lambda callback that is triggered when the user enters the text in the text box.
            //Essentially a lambda is a function without a name  and it is a block of code that you can treat as a variable and pass around
            // The lambda runs everytime the user types a character in the text box. "It" is the default name for the new text the user just typed
            onValueChange = {amountInput = it},
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier

){

    //TextField is composable function that lets the user enter the text in an app
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(stringResource(R.string.bill_amount))},
        //singleLine condenses the text box to a single horizontal scrollable line
        singleLine = true,
        //Can configure keyboard displayed on the screen to enter digits, email addresses, URLS and passwords
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        // This value parameter is a text box that displays the string value you pass here
        modifier = modifier,
    )
}



/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 * Private: Only visible within that class. Top-level: only visible within that specific file. In this instance,
 * calculateTi is defined outside of mainActivity. Because it is marked as private, it can only be called by other functions inside MainActivity like TipTime Layout
 * When to use it:
 * As a helper (for logic specific to the current file or class that shouldn't be touched by the rest of the app.
 * Rule of thumb: always start with private, only open it up if you actually need to use it elsewhere.
 * Internal: Visible anywhere inside the module. In this instance, "app" folder is the one module.. If calculateTip is internal, every file in app module can see it.
 */

private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout()
    }
}
