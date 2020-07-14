package com.example.around.ui.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.example.around.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

  @Rule
  @JvmField
  var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

  @Rule
  @JvmField
  var mGrantPermissionRule =
    GrantPermissionRule.grant(
      "android.permission.ACCESS_FINE_LOCATION"
    )

  @Test
  fun mainActivityTest() {
    val appCompatEditText = onView(
      allOf(
        withId(R.id.editText_coupon_search),
        childAtPosition(
          childAtPosition(
            withId(R.id.nav_host_fragment),
            0
          ),
          0
        ),
        isDisplayed()
      )
    )
    appCompatEditText.perform(replaceText("food"), closeSoftKeyboard())

    val appCompatEditText2 = onView(
      allOf(
        withId(R.id.editText_coupon_search), withText("food"),
        childAtPosition(
          childAtPosition(
            withId(R.id.nav_host_fragment),
            0
          ),
          0
        ),
        isDisplayed()
      )
    )
    appCompatEditText2.perform(pressImeActionButton())

    val appCompatButton = onView(
      allOf(
        withId(android.R.id.button1), withText("OK"),
        childAtPosition(
          childAtPosition(
            withId(R.id.buttonPanel),
            0
          ),
          3
        )
      )
    )
    appCompatButton.perform(scrollTo(), click())
  }

  private fun childAtPosition(
    parentMatcher: Matcher<View>, position: Int
  ): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("Child at position $position in parent ")
        parentMatcher.describeTo(description)
      }

      public override fun matchesSafely(view: View): Boolean {
        val parent = view.parent
        return parent is ViewGroup && parentMatcher.matches(parent)
          && view == parent.getChildAt(position)
      }
    }
  }
}
