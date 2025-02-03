package com.example.inkspire.ui.theme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ExpressionText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color
) {
    Text(
        text = text,
        color = color,
        modifier = modifier,
        style = InkSpireTheme.typography.displayMedium
    )
}

@Composable
fun IconText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
) {
    Text(
        text = text,
        color = color,
        modifier = modifier,
        style = InkSpireTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
}