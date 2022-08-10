package com.gooner.befootball.featurehome

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gooner.befootball.R
import com.gooner.befootball.ui.theme.*
import com.gooner.befootball.util.BarsColors

@Composable
fun HomeScreen() {

    val context = LocalContext.current

    BarsColors(
        statusBarColor = if (isSystemInDarkTheme()) BackgroundColorDark else StatusBarColorLight,
        navigationBarColor = if (isSystemInDarkTheme()) NavigationBarColorDark else NavigationBarColorLight
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        AppLogoContainer()
        LiveMatchesLeagues(context)
    }

}

@Composable
fun AppLogoContainer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_logo_app),
            contentDescription = "appLogo",
            colorFilter = ColorFilter.tint(
                if (isSystemInDarkTheme()) LogoColorDark else LogoColorLight
            )
        )
    }
}

@Composable
fun LiveMatchesLeagues(
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 24.dp, bottomStart = 24.dp
                )
            )
            .background(LiveMatchesLeagueColor)
    ) {
        Text(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 24.dp,
                    bottom = 8.dp
                ),
            text = stringResource(id = R.string.live_matches),
            color = Color.White,
            style = typography.h6
        )
        LiveMatchesLeaguesContent(context)
    }
}

@Composable
fun LiveMatchesLeaguesContent(
    context: Context
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        for (i in 1..5) {
            LeagueIcon(context, i)
        }
    }
}

@Composable
fun LeagueIcon(
    context: Context,
    leagueName: Int
) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp, start = 16.dp, bottom = 24.dp)
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp)
                .clickable {
                    Toast.makeText(context, "Clicked on league: $leagueName", Toast.LENGTH_SHORT).show()
                },
            painter = painterResource(id = R.drawable.ic_uefa_europa_league),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
