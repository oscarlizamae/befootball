package com.gooner.befootball.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = ColorPrimaryDark,
    primaryVariant = ColorVariantDark,
    background = BackgroundColorDark,
    onPrimary = ColorTextPrimaryDark
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = ColorPrimaryLight,
    primaryVariant = ColorVariantLight,
    background = BackgroundColorLight,
    onPrimary = ColorTextPrimaryLight

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun BeFootballTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}