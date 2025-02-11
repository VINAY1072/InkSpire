package com.example.inkspire.ui.theme

import androidx.compose.ui.graphics.Color

private val mainColor = Color(0xFFC04000)
private val white = Color(0xFFEFEFEF)
private val black = Color(0xFF151515)
private val divider = Color(0xFFBBBBBB)
private val darkModeCard = Color(0xFF212121)
private val lightModeCard = Color(0xFFFFFFFF)
private val lightModeDescription = Color(0xFF616161)
private val darkModeDescription = Color(0xFFBDBDBD)
private val lightModeTime = Color(0xFF9E9E9E)
private val darkModeTime = Color(0xFF757575)

data class Colors(
    val primary: Color,
    val secondary: Color,
    val divider: Color,
    val onPrimary: Color,
    val white: Color,
    val card: Color,
    val description: Color,
    val time: Color
)

val LightColors = Colors(
    primary = mainColor,
    secondary = black,
    divider = divider,
    onPrimary = white,
    white = white,
    card = lightModeCard,
    description = lightModeDescription,
    time = lightModeTime
)

val DarkColors = Colors(
    primary = mainColor,
    secondary = white,
    divider = divider,
    onPrimary = black,
    white = white,
    card = darkModeCard,
    description = darkModeDescription,
    time = darkModeTime
)