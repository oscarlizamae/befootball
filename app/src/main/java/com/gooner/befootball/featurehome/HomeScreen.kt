package com.gooner.befootball.featurehome

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gooner.befootball.R
import com.gooner.befootball.ui.theme.*
import com.gooner.befootball.util.BarsColors
import com.gooner.domain.model.League
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen() {

    val context = LocalContext.current
    val homeScreenViewModel = getViewModel<HomeScreenViewModel>()
    val leagues by remember { homeScreenViewModel.leagues }

    LaunchedEffect(key1 = true) {
        homeScreenViewModel.fetchCurrentLeagues()
    }

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
        LiveMatchesLeagues(leagues = leagues)
        LiveMatches()
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
    leagues: List<League>
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
        LiveMatchesLeaguesContent(leagues = leagues)
    }
}

@Composable
fun LiveMatchesLeaguesContent(
    leagues: List<League>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        /* for (i in 1..5) {
            LeagueIcon(
                leagueName = leagues[i].name,
                logoUrl = leagues[i].logoUrl
            )
        } */
        leagues.forEachIndexed { index, league ->
            if (index < 10) {
                LeagueIcon(
                    leagueName = league.name,
                    logoUrl = league.logoUrl
                )
            }
        }
    }
}

@Composable
fun LeagueIcon(
    leagueName: String,
    logoUrl: String
) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp, start = 12.dp, bottom = 24.dp, end = 4.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(logoUrl)
                .build(),
            contentDescription = leagueName,
            placeholder = painterResource(id = R.drawable.ic_uefa_europa_league),
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp)
                .clickable { }
                .background(Color.White),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun LiveMatches() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.live_matches),
                style = typography.h6,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = stringResource(id = R.string.live_matches),
                style = typography.body2,
                color = MaterialTheme.colors.onPrimary
            )
        }
        LiveMatchesContainer(color = LiveMatchCardColor)
    }
}

@Composable
fun LiveMatchesContainer(
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        for (i in 1..5) {
            LiveMatchCard(color = color)
        }
    }
}

@Composable
fun LiveMatchCard(
    color: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(end = 16.dp, top = 8.dp)
                .fillMaxWidth()
                .clickable { }
                .widthIn(0.dp, 125.dp),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = color
        ) {
            GameInfo()
        }
    }
}

@Composable
fun GameStatus() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 12.dp,
                    top = 2.dp,
                    bottom = 2.dp,
                    end = 16.dp
                ),
                text = "Live",
                style = typography.body2,
                fontWeight = FontWeight.Bold,
                color = LiveMatchCardColor
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .size(8.dp, 8.dp)
                .clip(CircleShape)
                .background(IndicatorCircleGreen)
        )
    }
}

@Composable
fun GameInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
    ) {
        GameStatus()
        Teams()
        MatchScore()
    }
}

@Composable
fun Teams() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(36.dp),
            painter = painterResource(id = R.drawable.real_madrid),
            contentDescription = null
        )
        Image(
            modifier = Modifier.size(36.dp),
            painter = painterResource(id = R.drawable.arsenal),
            contentDescription = null
        )
    }
}

@Composable
fun MatchScore() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 10.dp, start = 8.dp, end = 8.dp)
    ) {
        TeamScore(teamName = "Real Madrid", score = "1")
        TeamScore(teamName = "Arsenal", score = "1")
    }
}

@Composable
fun TeamScore(
    teamName: String,
    score: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = teamName,
            style = typography.body2,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = score,
            style = typography.body2,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
