package com.example.app.ui.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.data.models.Note
import com.example.app.ui.theme.AppTheme
import com.google.accompanist.glide.rememberGlidePainter
import java.text.DateFormat
import java.util.Date


@Composable
fun NoteItem(note: Note, onItemClick: (Note) -> Unit) {
    Row(modifier = Modifier.clickable { onItemClick(note) }) {
        Image(
            painter = rememberGlidePainter(
                request = note.image,
                previewPlaceholder = R.drawable.sample_feature_image,
                fadeIn = true,
            ),
            contentDescription = "URL: ${note.image}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(16.dp)
                .height(80.dp)
                .width(80.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                maxLines = 2,
                color = MaterialTheme.colors.primary,
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    note.author,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .alignByBaseline()
                        .weight(1f),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    DateFormat.getDateInstance().format(note.createdAt),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.overline,
                    modifier = Modifier.alignByBaseline(),
                )
            }
        }
    }
}

@Preview
@Composable
fun NoteItemPreview() {
    val note = Note(
        id = 1,
        title = "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        author = "John Doe",
        createdAt = Date(),
        image = "https://picsum.photos/id/870/1280/720",
        note = ""
    )

    AppTheme {
        NoteItem(note = note, onItemClick = {})
    }
}