package com.example.tiptime

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

/*
Test methods don't use logic like regular app methods. They aren't concerned with how something is implemented, they strictly check an expected output for a given input.
Tests typically end with some sort of assertion, which is used to ensure that a given condition is met.
 */


// Creating a test for the calculation of a 20% tip for a $10 bill amount. Expected outcome: $2
class TipCalculatorTests{
    @Test
    fun calculate_20_percent_tip_no_roundup(){
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount, tipPercent, roundUp = false)

    /*
    We want to ensure that the actual tip is equal to the expected tip.
    assertEquals() takes 2 parameters: An expected value and an actual value
    */
        assertEquals(expectedTip, actualTip)
    }
}