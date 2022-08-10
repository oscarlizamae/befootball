package com.gooner.befootball.featurehome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gooner.befootball.R
import com.gooner.befootball.ui.theme.*
import com.gooner.befootball.util.BarsColors

@Composable
fun HomeScreen() {

    BarsColors(
        statusBarColor = if (isSystemInDarkTheme()) BackgroundColorDark else StatusBarColorLight,
        navigationBarColor = if (isSystemInDarkTheme()) NavigationBarColorDark else NavigationBarColorLight
    )

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppLogo()
        }
    }

}

@Composable
fun AppLogo() {
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = painterResource(id = R.drawable.ic_logo_app),
        contentDescription = "appLogo",
        colorFilter = ColorFilter.tint(
            if (isSystemInDarkTheme()) LogoColorDark else LogoColorLight
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
