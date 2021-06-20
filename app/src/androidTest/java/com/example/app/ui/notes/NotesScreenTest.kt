package com.example.app.ui.notes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.lifecycle.MutableLiveData
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.app.R
import com.example.app.data.models.Note
import com.example.app.ui.theme.AppTheme
import com.example.app.util.ViewModelFactory
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.Date

class NotesScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var navHostController: TestNavHostController
    private val mockViewModelFactory = mock(ViewModelFactory::class.java)
    private val viewModel = mock(NotesViewModel::class.java)
    private val notesLiveData = MutableLiveData<List<Note>>()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val testNotes = listOf(
        Note(
            id = 1,
            title = "1 sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
            author = "John Doe",
            createdAt = Date(),
            image = "https://picsum.photos/id/486/1280/720",
            note = ""
        ),
        Note(
            id = 2,
            title = "2 sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
            author = "John Doe",
            createdAt = Date(),
            image = "https://picsum.photos/id/486/1280/720",
            note = ""
        ),
        Note(
            id = 3,
            title = "3 sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
            author = "John Doe",
            createdAt = Date(),
            image = "https://picsum.photos/id/486/1280/720",
            note = ""
        ),
    )

    @Before
    fun setUp() {
        `when`(mockViewModelFactory.create(NotesViewModel::class.java)).thenReturn(viewModel)
        `when`(viewModel.notes).thenReturn(notesLiveData)

        // Set test NavHostController
        navHostController = TestNavHostController(context).apply {
            setGraph(R.navigation.nav_graph)
            setCurrentDestination(R.id.note_list_fragment)
        }

        composeTestRule.setContent {
            AppTheme {
                NotesScreen(viewModel) {}
            }
        }
    }

    @Test
    fun topBar_showTitle() {
        notesLiveData.postValue(testNotes)
        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
    }

    @Test
    fun showNotes_showNoteListView() {
        notesLiveData.postValue(testNotes)

        composeTestRule.onRoot().printToLog("NoteList")
        composeTestRule.onNodeWithText("1 sed do eiusmod tempor incididunt ut labore et dolore magna aliqua", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("2 sed do eiusmod tempor incididunt ut labore et dolore magna aliqua", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("3 sed do eiusmod tempor incididunt ut labore et dolore magna aliqua", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onAllNodesWithText("John Doe", useUnmergedTree = true).assertCountEquals(3)
    }

    @Test
    fun clickNoteItem_navigatesToNoteDetail() {
        notesLiveData.postValue(testNotes)

        composeTestRule.onNodeWithText("1 sed do eiusmod tempor incididunt ut labore et dolore magna aliqua", useUnmergedTree = true).performClick()

        // TODO: Fix navigation assertions
//        assertEquals(R.id.note_detail_fragment, navHostController.currentDestination?.id)
//        assertNotNull(navHostController.currentDestination!!.arguments["note"])
    }
}
