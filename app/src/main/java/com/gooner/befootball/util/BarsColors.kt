package com.gooner.befootball.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BarsColors(
    statusBarColor: Color,
    navigationBarColor: Color
) {
    rememberSystemUiController().apply {
        this.setStatusBarColor(statusBarColor)
        this.setNavigationBarColor(navigationBarColor)
    }
}