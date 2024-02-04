package com.composeapp.testScreen.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightThemeColors = lightColors(surface = Color.Gray)
private val darkThemeColors = darkColors(surface = Color.DarkGray)

@Composable
fun AppTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if(darkTheme) darkThemeColors else lightThemeColors
    ) {
        content.invoke()
    }
}