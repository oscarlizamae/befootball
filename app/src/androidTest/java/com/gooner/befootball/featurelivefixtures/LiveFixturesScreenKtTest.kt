package com.gooner.befootball.featurelivefixtures

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gooner.befootball.ui.theme.BeFootballTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LiveFixturesScreenKtTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            BeFootballTheme {
                LiveFixturesScreen(
                    onBackIconClicked = {  },
                    onFixtureClicked = {  }
                )
            }
        }
    }

    @Test
    fun verifyIfAllViewExists() {
        composeTestRule.onNodeWithTag("liveFixturesContainer")
    }
}