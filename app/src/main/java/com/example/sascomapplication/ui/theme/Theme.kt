package com.example.sascomapplication.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = White,
    secondary = SecondaryColor,
    background = BackgroundLight,
    surface = White,
    onSurface = DarkText
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    onPrimary = Black,
    secondary = SecondaryColor,
    background = BackgroundDark,
    surface = Black,
    onSurface = White
)

@Composable
fun SascomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes(), // ✅ Instantiated
        content = content
    )
}
