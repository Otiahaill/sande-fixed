package com.example.sande.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

/**
 * iOS Shapes — matches iOS corner radius design language
 * iOS uses rounded rectangles with specific radii for different element sizes
 */
object SandeShape {
    // Small elements: badges, chips, small buttons
    val small = RoundedCornerShape(8.dp)
    // Medium elements: buttons, chips, input fields
    val medium = RoundedCornerShape(10.dp)
    // Large elements: cards, sheets, dialogs
    val large = RoundedCornerShape(16.dp)
    // Extra large: full-screen cards, modal presentations
    val extraLarge = RoundedCornerShape(24.dp)
    // Pill-shaped: status indicators, segmented controls
    val pill = RoundedCornerShape(20.dp)

    // Card-specific shapes
    val card = RoundedCornerShape(20.dp)
    val cardSmall = RoundedCornerShape(12.dp)
    val kpiCard = RoundedCornerShape(24.dp)

    // Input field shape
    val inputField = RoundedCornerShape(10.dp)

    // Button shapes
    val button = RoundedCornerShape(10.dp)
    val buttonSmall = RoundedCornerShape(8.dp)

    // Segmented control / pill
    val segmented = RoundedCornerShape(8.dp)

    // Corner-based shape for custom clipping
    val customCard = CornerBasedShape(
        topLeft = 20.dp,
        topRight = 20.dp,
        bottomLeft = 20.dp,
        bottomRight = 20.dp
    )
}

/**
 * Helper to create corner-based shapes for specific UI patterns
 */
fun roundedCornerShape(radius: Dp) = RoundedCornerShape(radius)
