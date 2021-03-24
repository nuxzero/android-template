package com.example.app

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NoteListFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    @Test
    fun displayNoteList() {
        launch(MainActivity::class.java)
//        launchFragmentInContainer<NoteListFragment>()
    }
}
