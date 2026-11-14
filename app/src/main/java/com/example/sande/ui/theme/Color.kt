package com.example.sande.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// iOS seed color for Material dynamic color
private val iosSeedColor = Color(0xFF0A84FF)

/**
 * iOS Light ColorScheme — matches iOS light mode design language
 */
@Composable
fun SandeLightColorScheme() = lightColorScheme(
    primary = Color(0xFF0A84FF),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE3F2FD),
    onPrimaryContainer = Color(0xFF0066CC),
    secondary = Color(0xFF5E5CE6),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE4E4EC),
    onSecondaryContainer = Color(0xFF2B2B5A),
    tertiary = Color(0xFF3B82F6),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFD9E8FF),
    onTertiaryContainer = Color(0xFF1E4D8F),
    error = Color(0xFFFF3B30),
    errorContainer = Color(0xFFFFEEEE),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFF8C1D18),
    background = Color(0xFFF2F2F7),
    onBackground = Color(0xFF1C1C1E),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1C1C1E),
    surfaceVariant = Color(0xFFF2F2F7),
    onSurfaceVariant = Color(0xFF8E8E93),
    outline = Color(0xFFC6C6C8),
    outlineVariant = Color(0xFFE5E5EA),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFF1C1C1E),
    inverseOnSurface = Color(0xFFE5E5EA),
    inversePrimary = Color(0xFF5E97FF),
    surfaceDisabled = Color(0xFFF2F2F7),
    onSurfaceDisabled = Color(0xFFC7C7CC)
)

/**
 * iOS Dark ColorScheme — matches iOS dark mode design language
 */
@Composable
fun SandeDarkColorScheme() = darkColorScheme(
    primary = Color(0xFF0A84FF),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF1D4ED8),
    onPrimaryContainer = Color(0xFFE3F2FD),
    secondary = Color(0xFF8E8EEF),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFF2C2C2E),
    onSecondaryContainer = Color(0xFFE4E4EC),
    tertiary = Color(0xFF5E97FF),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF2C4C8F),
    onTertiaryContainer = Color(0xFFD9E8FF),
    error = Color(0xFFFF453A),
    errorContainer = Color(0xFF2B0A0A),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFFFFEEEE),
    background = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF000000),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF1C1C1E),
    onSurfaceVariant = Color(0xFF8E8E93),
    outline = Color(0xFF38383A),
    outlineVariant = Color(0xFF2C2C2E),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFFFFFFFF),
    inverseOnSurface = Color(0xFF000000),
    inversePrimary = Color(0xFF0A84FF),
    surfaceDisabled = Color(0xFF2C2C2E),
    onSurfaceDisabled = Color(0xFF48484A)
)

/**
 * Returns the appropriate iOS ColorScheme based on system theme setting.
 */
@Composable
fun SandeColorScheme() = if (isSystemInDarkTheme()) {
    SandeDarkColorScheme()
} else {
    SandeLightColorScheme()
}
