package com.gooner.befootball.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.gooner.befootball.ui.theme.NavigationBarColorLight

@Composable
fun BarsColors(
    statusBarColor: Color,
    navigationBarColor: Color = NavigationBarColorLight
) {
    rememberSystemUiController().apply {
        this.setStatusBarColor(statusBarColor)
        this.setNavigationBarColor(navigationBarColor)
    }
}