package com.example.app

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.app.data.NoteRepositoryImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NoteListFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: NoteRepositoryImpl

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun displayNoteList() {
        launchFragmentInHiltContainer<NoteListFragment>()
    }
}
