package com.example.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(colors = if (darkTheme) DarkColors() else LightColors(), content = content)
}

private fun DarkColors(): Colors = darkColors(
    primary = White,
    onPrimary = Black,
    background = Black,
    onBackground = Black,
)

private fun LightColors(): Colors = lightColors(
    primary = Black,
    onPrimary = White,
    background = White,
    onBackground = White,
)
