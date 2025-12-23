Tip Time - Solution Code
=================================

[Tutorial Link](https://developer.android.com/codelabs/basic-android-kotlin-compose-using-state?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-2-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-using-state#0)


Introduction
------------
The Tip Time app contains various UI elements for calculating a tip,
teaching about user input, and State in Compose.


 Concepts Covered
--------------
* State in UI
* How Compose uses state to display data
* How to add a text box
* Hoist a state
* Played with keyboardOptions for TextField
* Currency number formatting
* Rounding up with kotlin.math.ceil
* Applying leading icons to text fields
* Added vertical scrolling for landscape mode
* Local unit tests with assertEquals
* UI Tests using Rules, onNodeWithText.performTextInput() and assertExists()

Automated Tests (notes for myself)
--------------
Automated testing is code that checks to ensure that another piece of code that you wrote works correctly.
This repo will intro me to the basics of testing in Android. More advanced testing will come in later codelabs.

Local tests:
These directly tests small piece of code to ensure a function works expectedly.
They are run in a development environment without the need of a device or emulator.

Instrumentation tests:
Aka UI tests. 
It tests parts of the app that depend on the Android API and its platform APIs and services.
UI tests launch an app or part of an app simulate user interactions and check of the app reacts correctly.
When you run UI test on Android, the test code is built into the APK.
APK = compressed file that contains all the code necessary to run the app on a device or emulator.

Getting Started
---------------
1. Install Android Studio, if you don't already have it.
2. Download the sample.
3. Import the sample into Android Studio.
4. Build and run the sample.
