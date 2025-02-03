package com.example.inkspire.ui.theme

import androidx.compose.ui.graphics.Color

private val mainColor = Color(0xFFC04000)
private val white = Color(0xFFFFFFFF)
private val black = Color(0xFF151515)
private val divider = Color(0xFFBBBBBB)

data class Colors(
    val primary: Color,
    val secondary: Color,
    val divider: Color,
    val onPrimary: Color
)

val LightColors = Colors(
    primary = mainColor,
    secondary = black,
    divider = divider,
    onPrimary = white
)

val DarkColors = Colors(
    primary = mainColor,
    secondary = white,
    divider = divider,
    onPrimary = black
)