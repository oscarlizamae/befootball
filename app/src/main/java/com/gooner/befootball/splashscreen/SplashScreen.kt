package com.gooner.befootball.splashscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(onSplashScreenShowed: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(1500)
        onSplashScreenShowed()
    }
    SplashScreen()
}

@Composable
fun SplashScreen() {

}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}