
package com.example.watermeapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val GreenColorScheme = lightColorScheme(
    primary = Color(0xFF388E3C),
    onPrimary = Color.White,

    primaryContainer = Color(0xFFC8E6C9),
    onPrimaryContainer = Color(0xFF103B16),

    secondary = Color(0xFF558B2F),
    onSecondary = Color.White,

    background = Color(0xFFF7FAF5),
    onBackground = Color(0xFF1B1C19),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1B1C19),

    surfaceVariant = Color(0xFFE8F5E9),
    onSurfaceVariant = Color(0xFF424940)
)

@Composable
fun WaterMeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = GreenColorScheme,
        content = content
    )
}
