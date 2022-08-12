package com.example.cookbook.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 20.sp,
        fontFeatureSettings = "c2sc, smcp"
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 13.sp,
        fontFeatureSettings = "c2sc, smcp"
    )
)