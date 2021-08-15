package com.example.app.ui.note_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.R
import com.example.app.data.models.Note
import com.example.app.ui.theme.AppTheme
import com.google.accompanist.glide.rememberGlidePainter
import java.text.DateFormat
import java.util.Date

@Composable
fun NoteDetailScreen(noteId: Int, onBackPressed: () -> Unit) {
    val viewModel: NoteDetailViewModel = hiltViewModel()
    val note by viewModel.note.collectAsState(initial = Note.Empty)
    viewModel.setNoteId(noteId) // TODO: Refactor this
    NoteDetailScreen(note = note, onBackPressed = onBackPressed)
}

@Composable
fun NoteDetailScreen(note: Note, onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Note", color = MaterialTheme.colors.primary) },
                backgroundColor = androidx.compose.ui.graphics.Color.Transparent,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back button",
                            tint = MaterialTheme.colors.primary,
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    note.title,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(top = 16.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        note.author,
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.alignByBaseline(),
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        DateFormat.getDateInstance().format(note.createdAt),
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.overline,
                        modifier = Modifier.alignByBaseline(),
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Image(
                    painter = rememberGlidePainter(
                        request = note.image,
                        previewPlaceholder = R.drawable.sample_feature_image,
                        fadeIn = true
                    ),
                    contentDescription = "URL: ${note.image}",
                    modifier = Modifier
                        .aspectRatio(1.56f),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    note.note,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                )
            }
        }
    )
}

@Preview
@Composable
fun NoteDetailScreenPreview() {
    val note = Note(
        id = 1,
        title = "Lsed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        author = "John Doe",
        createdAt = Date(),
        image = "https://picsum.photos/id/486/1280/720",
        note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis ultricies lacus sed turpis tincidunt id aliquet risus feugiat. Morbi tincidunt augue interdum velit euismod in pellentesque massa. Donec pretium vulputate sapien nec sagittis aliquam malesuada bibendum arcu. Tincidunt tortor aliquam nulla facilisi cras. Adipiscing at in tellus integer. Lorem donec massa sapien faucibus et molestie ac feugiat sed. Adipiscing elit ut aliquam purus sit amet luctus venenatis. Nisl nunc mi ipsum faucibus. A pellentesque sit amet porttitor. Mattis rhoncus urna neque viverra justo nec ultrices dui sapien.\\n\\nElementum nisi quis eleifend quam adipiscing vitae proin sagittis. Faucibus pulvinar elementum integer enim neque. Dapibus ultrices in iaculis nunc sed. Sit amet justo donec enim diam vulputate ut pharetra. Risus at ultrices mi tempus. Cursus in hac habitasse platea dictumst quisque sagittis purus sit. Lorem ipsum dolor sit amet consectetur adipiscing elit pellentesque. Imperdiet sed euismod nisi porta lorem mollis aliquam ut. Diam maecenas ultricies mi eget. Posuere lorem ipsum dolor sit amet consectetur adipiscing elit duis. Non diam phasellus vestibulum lorem sed risus ultricies tristique. In aliquam sem fringilla ut morbi tincidunt augue interdum. Lorem sed risus ultricies tristique nulla. Purus semper eget duis at tellus at urna condimentum. Feugiat vivamus at augue eget arcu dictum."
    )
    AppTheme(darkTheme = true) {
        NoteDetailScreen(note, onBackPressed = {})
    }
}