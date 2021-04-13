package com.example.app.ui.account

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.MutableLiveData
import com.example.app.data.models.Profile
import com.example.app.ui.theme.AppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class AccountScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val viewModel = mock(AccountViewModel::class.java)
    private val profileLiveData = MutableLiveData<Profile>()
    private val testProfile = Profile(
        id = 1,
        fullName = "John Doe",
        email = "john@mail.com",
        image = "https://picsum.photos/id/433/300/300",
    )

    @Before
    fun setUp() {
        `when`(viewModel.profile).thenReturn(profileLiveData)
        composeTestRule.setContent {
            AppTheme {
                AccountScreen(viewModel)
            }
        }
    }

    @Test
    fun topBar_showTitle() {
        profileLiveData.postValue(testProfile)

        composeTestRule.onNodeWithText("Account").assertIsDisplayed()
    }

    @Test
    fun showProfile_successful() {
        profileLiveData.postValue(testProfile)

        composeTestRule.onNodeWithText("John Doe").assertIsDisplayed()
        composeTestRule.onNodeWithText("john@mail.com").assertIsDisplayed()
    }

    @Test
    fun clickNotificationSetting_showSnackbar() {
        profileLiveData.postValue(testProfile)

        composeTestRule.onNodeWithText("Notifications").performClick()

        composeTestRule.onNodeWithText("Tapped notifications!").assertIsDisplayed()
    }
}
