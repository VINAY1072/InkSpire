package com.example.inkspire.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape

data class Shape(
    val smallRoundCornerShape: RoundedCornerShape = RoundedCornerShape(Radius().small),
    val mediumRoundCornerShape: RoundedCornerShape = RoundedCornerShape(Radius().medium),
    val largeRoundCornerShape: RoundedCornerShape = RoundedCornerShape(Radius().large),
    val extraLargeRoundCornerShape: RoundedCornerShape = RoundedCornerShape(Radius().extraLarge),
)