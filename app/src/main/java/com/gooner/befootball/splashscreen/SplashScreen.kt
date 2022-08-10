package com.gooner.befootball.splashscreen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gooner.befootball.R
import com.gooner.befootball.ui.theme.*
import com.gooner.befootball.util.BarsColors
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
    BarsColors(
        statusBarColor = if (isSystemInDarkTheme()) StatusBarColorSplashScreenDark else StatusBarColorLight,
        navigationBarColor = if (isSystemInDarkTheme()) NavigationBarColorDark else NavigationBarColorSplashScreenLight
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = if (isSystemInDarkTheme()) {
                        listOf(
                            MaterialTheme.colors.primaryVariant,
                            MaterialTheme.colors.primaryVariant
                        )
                    } else {
                        listOf(
                            MaterialTheme.colors.primary,
                            MaterialTheme.colors.primary
                        )
                    }
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(260.dp),
            painter = painterResource(
                id = R.drawable.ic_logo_app
            ),
            contentDescription = "app_logo",
            colorFilter = ColorFilter.tint(
                if (isSystemInDarkTheme()) LogoColorDark else LogoColorLight
            )
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}