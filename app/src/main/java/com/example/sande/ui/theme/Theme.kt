package com.example.sande.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun SandeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) SandeDarkColors else SandeLightColors
    MaterialTheme(colorScheme = colors, typography = SandeTypography) {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}
