package com.example.app.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.app.ui.theme.AppTheme


@Composable
fun NavigationUpButton(onNavigationIconClick: () -> Unit) {
    IconButton(onClick = { onNavigationIconClick() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back button",
            tint = MaterialTheme.colors.primary,
        )
    }
}

@Preview
@Composable
fun NavigationUpButtonPreview() {
    AppTheme {
        NavigationUpButton {}
    }
}

@Preview
@Composable
fun NavigationUpButtonDarkPreview() {
    AppTheme(darkTheme = true) {
        NavigationUpButton {}
    }
}
