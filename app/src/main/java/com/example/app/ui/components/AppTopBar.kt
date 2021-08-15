package com.example.app.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.note_detail.NoteDetailScreen
import com.example.app.ui.theme.AppTheme
import com.google.accompanist.insets.statusBarsPadding


@Composable
fun AppTopBar(modifier: Modifier = Modifier, title: String, navigationIcon: (@Composable () -> Unit)? = null) {
    Surface(modifier = modifier.statusBarsPadding()) {
        TopAppBar(
            title = { Text(title, color = MaterialTheme.colors.primary) },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            navigationIcon = navigationIcon,
        )
    }
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTheme {
        AppTopBar(title = "Title")
    }
}

@Preview
@Composable
fun AppTopBarWithNavigationUpPreview() {
    AppTheme {
        AppTopBar(title = "Title", navigationIcon = {
            NavigationUpButton {

            }
        })
    }
}

@Preview
@Composable
fun AppTopBarDarkPreview() {
    AppTheme(darkTheme = true) {
        AppTopBar(title = "Title")
    }
}

@Preview
@Composable
fun AppTopBarDarkWithNavigationUpPreview() {
    AppTheme(darkTheme = true) {
        AppTopBar(title = "Title", navigationIcon = {
            NavigationUpButton {

            }
        })
    }
}
