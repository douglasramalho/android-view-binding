package br.com.douglasmotta.viewbinding

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun shouldShowErrorOnMandatoryField_whenNameFieldIsEmpty() {
        onView(withId(R.id.buttonDoAction)).perform(click())
        onView(withId(R.id.textNameValidationError))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldAddNameToList_whenNameFieldValid() {
        onView(withId(R.id.inputName))
            .perform(typeText("Douglas"))

        onView(withId(R.id.buttonDoAction)).perform(click())

        onView(withId(R.id.recyclerNames))
            .perform(RecyclerViewActions
                .scrollTo<MainAdapter.MainViewHolder>(withText("Douglas")))

        onView(withText("Douglas")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowToastHelloName_whenAnItemOnListIsClicked() {

    }
}