package com.example.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.app.data.NoteRepositoryImpl
import com.example.app.data.models.Note
import com.example.app.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class NoteListViewModelTest {

    @get:Rule
    val instanceExecutor = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val noteRepository = mock(NoteRepositoryImpl::class.java)
    private val observer = mock(Observer::class.java) as Observer<List<Note>>

    @Test
    fun `observe notes successful`() = runBlockingTest {
        val expectedNotes = emptyList<Note>()
        `when`(noteRepository.getNoteList()).thenReturn(flowOf(expectedNotes))
        val viewModel = NoteListViewModel(noteRepository)

        val result = viewModel.notes

        result.observeForever(observer)
        assertEquals(expectedNotes, result.value)
    }
}