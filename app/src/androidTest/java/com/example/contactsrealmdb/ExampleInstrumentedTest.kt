package com.example.contactsrealmdb


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private fun clickItemWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id) as View
                v.performClick()
            }
        }
    }

    //Добавляем контакт
    @Test
    fun checkAddingContact_isSuccess() {
        onView(withId(R.id.fabAddContact))
            .check(matches(isDisplayed()))
            .perform(click())

        val name = "TestName"
        val surname = "TestSurname"
        val number = "999"

        onView(withId(R.id.etName))
            .perform(typeText(name))
            .check(matches(withText(name)))

        onView(withId(R.id.etSurname))
            .perform(typeText(surname))
            .check(matches(withText(surname)))

        onView(withId(R.id.etNumber))
            .perform(typeText(number))
            .check(matches(withText(number)))

        onView(withId(R.id.btnSave))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.rvContacts))
            .check(matches(isDisplayed()))

    }

    //Проверяем наличие элемента с данными контакта в RecyclerView
    @Test
    fun checkAddedContactIsVisible_isSuccess() {

        val name = "TestName TestSurname"

        onView(withId(R.id.rvContacts))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(
                        withText(
                            name
                        )
                    )
                )
            )
    }

    //Проверяем возможность редактирования контакта
    @Test
    fun checkEditingContact_isSuccess() {

        val name = "TestEditName"
        val surname = "TestEditSurname"
        val number = "9059999999"

        onView(withId(R.id.rvContacts))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    clickItemWithId(R.id.ivEdit)
                )
            )

        onView(withId(R.id.etName))
            .perform(typeText(name))
            .check(matches(withText(name)))

        onView(withId(R.id.etSurname))
            .perform(typeText(surname))
            .check(matches(withText(surname)))

        onView(withId(R.id.etNumber))
            .perform(typeText(number))
            .check(matches(withText(number)))
    }

    //Проверяем возможность удаления контакта
    @Test
    fun checkEditedContactDelete_isSuccess() {

        onView(withId(R.id.rvContacts))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    clickItemWithId(R.id.ivDelete)
                )
            )
    }
}