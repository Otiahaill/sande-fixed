package com.example.sande.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

/**
 * iOS Typography Scale — matches San Francisco system font characteristics
 * iOS uses optical sizes: text (17pt and below), display (20pt and above)
 */
val SandeTypography = Typography(
    // Display styles (large headlines, titles)
    displayLarge = TextStyle(
        fontSize = 34.sp, fontWeight = FontWeight(400), letterHeight = -0.02.em
    ), // iOS largeTitle (~34pt)
    displayMedium = TextStyle(
        fontSize = 28.sp, fontWeight = FontWeight(400), letterHeight = -0.02.em
    ), // iOS title1 (~28pt)
    displaySmall = TextStyle(
        fontSize = 22.sp, fontWeight = FontWeight(500), letterHeight = -0.02.em
    ), // iOS title2 (~22pt)

    // Headline styles
    headlineLarge = TextStyle(
        fontSize = 28.sp, fontWeight = FontWeight(500), letterHeight = -0.02.em
    ),
    headlineMedium = TextStyle(
        fontSize = 24.sp, fontWeight = FontWeight(500), letterHeight = -0.02.em
    ),
    headlineSmall = TextStyle(
        fontSize = 20.sp, fontWeight = FontWeight(500), letterHeight = -0.02.em
    ),

    // Title styles
    titleLarge = TextStyle(
        fontSize = 22.sp, fontWeight = FontWeight(500), letterHeight = -0.02.em
    ),
    titleMedium = TextStyle(
        fontSize = 18.sp, fontWeight = FontWeight(500), letterHeight = -0.02.em
    ),
    titleSmall = TextStyle(
        fontSize = 16.sp, fontWeight = FontWeight(500), letterHeight = -0.01.em
    ),

    // Body styles (iOS body ~17pt, callout ~16pt)
    bodyLarge = TextStyle(
        fontSize = 17.sp, fontWeight = FontWeight(400), letterHeight = -0.02.em,
        textDecoration = TextDecoration.NoDecoration
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp, fontWeight = FontWeight(400), letterHeight = -0.02.em
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp, fontWeight = FontWeight(400), letterHeight = -0.02.em
    ),

    // Label styles (iOS footnote ~13pt, caption1 ~12pt, caption2 ~11pt)
    labelLarge = TextStyle(
        fontSize = 17.sp, fontWeight = FontWeight(500), letterHeight = -0.01.em
    ),
    labelMedium = TextStyle(
        fontSize = 13.sp, fontWeight = FontWeight(500), letterHeight = -0.01.em
    ),
    labelSmall = TextStyle(
        fontSize = 11.sp, fontWeight = FontWeight(500), letterHeight = -0.02.em
    )
)
