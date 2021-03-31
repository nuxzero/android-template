package com.example.app.ui.notes

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.app.R
import com.example.app.data.models.Note
import com.example.app.util.ViewModelFactory
import com.example.app.util.onAttached
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class NoteListFragmentTest {
    private lateinit var navHostController: TestNavHostController
    private val mockViewModelFactory = mock(ViewModelFactory::class.java)
    private val mockViewModel = mock(NoteListViewModel::class.java)
    private val notesLiveData = MutableLiveData<List<Note>>()

    @Before
    fun setUp() {
        `when`(mockViewModelFactory.create(NoteListViewModel::class.java)).thenReturn(mockViewModel)
        `when`(mockViewModel.notes).thenReturn(notesLiveData)

        val scenario = launchFragmentInContainer(null, R.style.Theme_Templateapp) {
            NoteListFragment().apply {
                onAttached {
                    viewModelFactory = mockViewModelFactory
                }
            }
        }

        // Set test NavHostController
        navHostController = TestNavHostController(ApplicationProvider.getApplicationContext()).apply {
            setGraph(R.navigation.nav_graph)
            setCurrentDestination(R.id.note_list_fragment)
        }
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navHostController)
        }
    }

    @Test
    fun showNotes_shouldDisplayNotes() {
        notesLiveData.postValue(emptyList())

        onView(withId(R.id.note_list)).check(matches(isDisplayed()))
//        onView(withId(R.id.highlight_product_list)).check(ViewAssertions.matches(itemCount(products.size)))
    }
}
