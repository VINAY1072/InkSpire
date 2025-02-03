package com.example.inkspire.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val localDimens = staticCompositionLocalOf { Dimens() }
private val localColorScheme = staticCompositionLocalOf { LightColors }
private val localRadius = staticCompositionLocalOf { Radius() }
private val localShape = staticCompositionLocalOf { Shape() }
private val localTypography = staticCompositionLocalOf { Typography() }

object InkSpireTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = localColorScheme.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = localTypography.current

    val radius: Radius
        @Composable
        @ReadOnlyComposable
        get() = localRadius.current

    val shape: Shape
        @Composable
        @ReadOnlyComposable
        get() = localShape.current

    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = localDimens.current
}

@Composable
fun InkSpireTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    val colorScheme = if (useDarkTheme) DarkColors else LightColors

    val typography = Typography(
        displayLarge = displayLarge(),
        displayMedium = displayMedium(),
        displaySmall = displaySmall(),
        headlineLarge = headlineLarge(),
        headlineMedium = headlineMedium(),
        headlineSmall = headlineSmall(),
        titleLarge = titleLarge(),
        titleMedium = titleMedium(),
        titleSmall = titleSmall(),
        bodyLarge = bodyLarge(),
        bodyMedium = bodyMedium(),
        bodySmall = bodySmall(),
        labelLarge = labelLarge(),
        labelMedium = labelMedium(),
        labelSmall = labelSmall(),
    )

    CompositionLocalProvider(
        localColorScheme provides colorScheme,
        localTypography provides typography,
        localDimens provides Dimens(),
        localRadius provides Radius(),
    ) {
        content()
    }
}